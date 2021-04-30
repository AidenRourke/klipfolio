import {render, fireEvent, waitFor} from '@testing-library/react'
import Search from './Search';
import {rest} from 'msw'
import {setupServer} from 'msw/node'

const metrics_url = 'http://localhost:8080/metrics/search/findByNameContainingIgnoreCase';
const services_url = 'http://localhost:8080/services/search/findByNameContainingIgnoreCase';
const modeled_datas_url = 'http://localhost:8080/modeledDatas/search/findByNameContainingIgnoreCase';

const asset = (id) => {
    return {
        name: "test",
        _links: {
            self: {
                href: "test" + id,
            }
        }
    }
};

const links = {
    self: {
        href: "test"
    }
};

const mockMetricsResponse = {
    _embedded: {
        metrics: [asset(1)]
    },
    _links: links
};

const mockServicesResponse = {
    _embedded: {
        services: [asset(2)]
    },
    _links: links
};

const mockModeledDatasResponse = {
    _embedded: {
        modeledDatas: [asset(3)]
    },
    _links: links
};

const server = setupServer(
    rest.get(metrics_url, (req, res, ctx) => {
        return res(ctx.json(mockMetricsResponse))
    }),
    rest.get(services_url, (req, res, ctx) => {
        if (!!req.url.searchParams.get('name').includes("query")) {
            mockServicesResponse._embedded.services = []
        }
        return res(ctx.json(mockServicesResponse))
    }),
    rest.get(modeled_datas_url, (req, res, ctx) => {
        return res(ctx.json(mockModeledDatasResponse))
    })
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('renders Search component', () => {
    render(<Search/>);
});

test('renders options after focus', async () => {
    const {getByTestId, queryAllByText} = render(<Search/>);

    expect(queryAllByText("test")).toHaveLength(0);

    fireEvent.focus(getByTestId("input"));

    await waitFor(() => expect(queryAllByText("test")).toHaveLength(3));
});

test('clicking tag changes classname', () => {
    const {getByTestId, getByText} = render(<Search/>);

    fireEvent.focus(getByTestId("input"));

    expect(getByText("Metrics")).not.toHaveClass("Tag-active")

    fireEvent.click(getByText("Metrics"));

    expect(getByText("Metrics")).toHaveClass("Tag-active")
});

test('clicking tag filters options', async () => {
    const {getByTestId, getByText, getAllByText} = render(<Search/>);

    fireEvent.focus(getByTestId("input"));

    fireEvent.click(getByText("Metrics"));

    await waitFor(() => expect(getAllByText("test")).toHaveLength(1))
});

test('submitting form changes the query', async () => {
    const {getByTestId, getAllByText} = render(<Search/>);

    fireEvent.focus(getByTestId("input"));

    fireEvent.change(getByTestId("input"), {target: {value: 'query'}});

    fireEvent.click(getByTestId("submit"));

    await waitFor(() => expect(getAllByText("test")).toHaveLength(2))
});

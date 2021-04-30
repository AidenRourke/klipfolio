import {render} from '@testing-library/react'
import Metrics from './Metrics';
import {rest} from 'msw'
import {setupServer} from 'msw/node'

const url = 'http://localhost:8080/metrics';

const mockResponse = {
    _embedded: {
        metrics: [
            {
                name: "test",
                id: "test",
                symbol: "test",
                _links: {
                    self: {
                        href: "test"
                    }
                }
            }
        ]
    },
    _links: {
        self: {
            href: "test"
        },
        next: {
            href: "test"
        }
    },
    page: {
        number: 0,
        totalPages: 2,
    }
};

const server = setupServer(
    rest.get(url, (req, res, ctx) => {
        return res(ctx.json(mockResponse))
    })
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

test('renders Metrics component', () => {
    render(<Metrics/>);
});

test('renders Metric Widgets', async () => {
    const {findAllByText} = render(<Metrics/>);

    expect(await findAllByText("test")).toHaveLength(1);
});

test("displays show more", async () => {
    const {findByText} = render(<Metrics/>);

    expect(await findByText("More metrics")).toBeInTheDocument()
});

test("displays show less", async () => {
    mockResponse.page.number = 1;

    server.use(
        rest.get(url, (req, res, ctx) => {
            return res(ctx.json(mockResponse))
        })
    );

    const {findByText} = render(<Metrics/>);

    expect(await findByText("Less metrics")).toBeInTheDocument()
});

test("displays show less and show more", async () => {
    mockResponse.page.totalPages = 3;

    server.use(
        rest.get(url, (req, res, ctx) => {
            return res(ctx.json(mockResponse))
        })
    );

    const {findByText} = render(<Metrics/>);

    expect(await findByText("Less metrics")).toBeInTheDocument();
    expect(await findByText("More metrics")).toBeInTheDocument();
});


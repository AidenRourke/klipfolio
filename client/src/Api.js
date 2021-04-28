const BASE_URL = "http://localhost:8080";

// Repositories
const METRICS = "metrics";
const SERVICES = "services";
const METRIC_VALUES = "metricValues";
const MODELED_DATA = "modeledDatas";

// Search
const SEARCH = "search";

// Metric Value Search Queries
const ALL_BY_METRIC_ID_AND_TIMERANGE = "findAllByMetricIdAndCreatedIsBetweenOrderByCreatedAsc";
const LAST_BY_METRIC_ID_AND_TIMEEND = "findFirstByMetricIdAndCreatedIsBeforeOrderByCreatedDesc";

// Find By Name Search Query
const ALL_BY_NAME_CONTAINS = "findByNameContainingIgnoreCase";

// Query Parameters
const SIZE = "size";
const METRIC_ID = "metricId";
const TIME_START = "timeStart";
const TIME_END = "timeEnd";
const NAME = "name";

// Defaults
const PAGE_SIZE = 1;

const getMetrics = () => {
    return fetch(`${BASE_URL}/${METRICS}?${SIZE}=${PAGE_SIZE}`);
};

const findMetrics = (name) => {
    return fetch(`${BASE_URL}/${METRICS}/${SEARCH}/${ALL_BY_NAME_CONTAINS}?${NAME}=${name}&${SIZE}=${PAGE_SIZE}`)
};

const getServices = () => {
    return fetch(`${BASE_URL}/${SERVICES}?${SIZE}=${PAGE_SIZE}`);
};

const findServices = (name) => {
    return fetch(`${BASE_URL}/${SERVICES}/${SEARCH}/${ALL_BY_NAME_CONTAINS}?${NAME}=${name}&${SIZE}=${PAGE_SIZE}`)
};

const getMetricValues = (metricId, timeStart, timeEnd) => {
    return fetch(`${BASE_URL}/${METRIC_VALUES}/${SEARCH}/${ALL_BY_METRIC_ID_AND_TIMERANGE}?${METRIC_ID}=${metricId}&${TIME_START}=${timeStart}&${TIME_END}=${timeEnd}`)
};

const getLastMetricValue = (metricId, timeEnd) => {
    return fetch(`${BASE_URL}/${METRIC_VALUES}/${SEARCH}/${LAST_BY_METRIC_ID_AND_TIMEEND}?${METRIC_ID}=${metricId}&${TIME_END}=${timeEnd}`)
};

const getModeledData = () => {
    return fetch(`${BASE_URL}/${MODELED_DATA}?${SIZE}=${PAGE_SIZE}`);
};

const findModeledData = (name) => {
    return fetch(`${BASE_URL}/${MODELED_DATA}/${SEARCH}/${ALL_BY_NAME_CONTAINS}?${NAME}=${name}&${SIZE}=${PAGE_SIZE}`)
};

export {getMetrics, getServices, findMetrics, findServices, getMetricValues, getLastMetricValue, getModeledData, findModeledData}

import axios from '../utils/axios'

export function publishEntrust(params) {
    return axios.post('/entrust/add', params);
}

export function listEntrust(params) {
    return axios.post('/entrust/list', params);
}

export function listEntrustById(params) {
    return axios.post('/entrust/listById', params);
}

export function editAndPublishEntrust(params) {
    return axios.post('/goods/add', params);
}

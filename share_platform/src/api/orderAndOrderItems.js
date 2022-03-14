import axios from '../utils/axios'

export function addOrderItem(params) {
    return axios.post('/orderItem/add', params);
}

export function addOrder(params) {
    return axios.post('/order/add', params);
}

export function listOrderItem(params) {
    return axios.post('/orderItem/listByUser', params);
}

export function listOrderByUser(params) {
    return axios.post('/order/listByUser', params);
}

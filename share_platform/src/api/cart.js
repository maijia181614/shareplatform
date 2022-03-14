import axios from '../utils/axios'

export function addCart(params) {
    return axios.post('/cart/add', params);
}

export function listCartItem(params) {
    return axios.post('/cart/list', params);
}

export function deleteItem(params) {
    return axios.post('/cart/delete', params);
}

export function addOrderByCart(params) {
    return axios.post('/order/addOrderByCart', params);
}

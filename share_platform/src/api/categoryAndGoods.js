import axios from '../utils/axios'

export function listCategory(params) {
    return axios.post('/category/list', params);
}

export function listGoods(params) {
    return axios.post('/goods/search', params);
}

export function goodsDetail(params) {
    return axios.post('/goods/detail', params);
}

export function listChildrenCategory(params) {
    return axios.post('/category/listChildrenCategory', params);
}

export function listGoodsByOrder(params) {
    return axios.post('/goods/searchByOrder', params);
}

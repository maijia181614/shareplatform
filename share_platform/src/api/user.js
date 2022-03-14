import axios from '../utils/axios'

export function login(params) {
    return axios.post('/home/login', params);
}

export function register(params) {
    return axios.post('/home/register', params);
}

export function userInfo(params) {
    return axios.post('/user/userInfo', params);
}

export function logout(params) {
    return axios.post('/home/logout', params);
}

export function isAdmin(params) {
    return axios.post('/user/isAdmin', params);
}

export function editInfo(params) {
    return axios.post('/user/editUserInfo', params);
}

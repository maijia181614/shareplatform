import axios from '../utils/axios'

export function indexList(params) {
    return axios.post('/index-config/list', params);
}

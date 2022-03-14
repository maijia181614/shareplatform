import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8088'//后端地址
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers['authToken'] = localStorage.getItem('token') || '' //每次请求都使用token,登录后由shiro发布的sessionId
axios.defaults.headers.post['Content-Type'] = 'application/json'

axios.interceptors.response.use(res => {
    //请求不成功，返回拒绝promise
    if (res.status !== 200) {
        console.log(res.status)
        return Promise.reject(res)
    }
    console.log(localStorage.getItem('token'))
    console.log(res.data)
    return res.data
})

/* 请求拦截 */
//每次请求带上token
axios.interceptors.request.use(
    config => {
        console.log(localStorage.getItem("token"))
        if (localStorage.getItem("token") != null) {
            config.headers["authToken"] = localStorage.getItem("token");
        }

        return config;
    },
    err => Promise.reject(err)
);

export default axios

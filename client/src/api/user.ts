import request from './request'

interface LoginData {
    username: string
    password: string
}

export function login(data: LoginData) {
    return request.post('/user/login', data)
}

export function getUserInfo() {
    return request.get('/user/info')
}

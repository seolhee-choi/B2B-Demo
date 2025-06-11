import apiClient from "../utils/ApiClient.js";

const testGet = (params) => {
    return apiClient.get('/api/test',{ ...params })
        .then(res => res.data)
        .catch(err => console.log(err));
}

const testPost = (params) => {
    return apiClient.post('/api/test',{ ...params })
        .then(res => res.data)
        .catch(err => console.log(err));
}

export default {
    testGet,
    testPost,
}
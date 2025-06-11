import axios from "axios";

const baseURL = import.meta.env.VITE_API_URL;
const headers = {
  'Content-Type': 'application/json',
};

const instance = axios.create({
  baseURL: baseURL,
  headers: headers,
})

instance.interceptors.request.use(request => {
  return request;
})

instance.interceptors.response.use(response => {
  return response;
})

const apiClient = {
  get: (url, params) => instance.get(url, {params, headers}),
  post: (url, params) => instance.post(url, params, {headers}),
};

export default apiClient;

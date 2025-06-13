import apiClient from "@/utils/ApiClient.js";


const fetchBookmarks = (params) => {
  return apiClient.post('/api/',{ ...params })
    .then(res => res.data)
    .catch(err => console.log(err));
}
const saveBookmark = (params) => {
  return apiClient.post('/api/',{ ...params })
    .then(res => res.data)
    .catch(err => console.log(err));
}
const deleteBookMark = (params) => {
  return apiClient.post('/api/',{ ...params })
    .then(res => res.data)
    .catch(err => console.log(err));
}

export default {
  fetchBookmarks,
  saveBookmark,
  deleteBookMark,
}

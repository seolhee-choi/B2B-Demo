import apiClient from "@/utils/ApiClient.js";


/*Todo : 로그인 화면 추가 예정
* param : roleId? userId?
*/
const findMenuByRoles = (params) => {
  return apiClient.post('/api/',{ ...params })
    .then(res => res.data)
    .catch(err => console.log(err));
}

export default {
  findMenuByRoles
}

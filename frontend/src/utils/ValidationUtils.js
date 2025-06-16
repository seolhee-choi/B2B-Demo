/* 입력값 정규식 및 유효성검사 */
import utils from '@/utils/CommonUtils.js'


const validateLogin = ({ id, password }) => {
  const errors = {};

  if (utils.isEmptyString(id)) {
    errors.id = "아이디를 입력해주세요.";
  } else if (!utils.isValidId(id)) {
    errors.id = "아이디 형식이 올바르지 않습니다 (영문 + 숫자 조합)";
  }

  if (utils.isEmptyString(password)) {
    errors.password = "비밀번호를 입력해주세요.";
  } else if (!utils.isValidPassword(password)) {
    errors.password = "비밀번호 형식이 올바르지 않습니다.";
  }

  return errors;
}

export default {
  validateLogin
}

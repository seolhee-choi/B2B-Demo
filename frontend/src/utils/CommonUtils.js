/* 데이터 조작 및 체크 함수 범용 파일 */
import { _ } from '@/utils/lodash.js'

/*
* 정규식
* */
const idRegex = /^[A-Za-z0-9]{5,20}$/;
const passwordRegex = /^[A-Za-z0-9]{9,12}$/;

/*
* null 또는 undefined인지 간단히 판단하기 위한 함수
* */
const isEmpty = (arg) => {
  return _.isNil(arg) || _.toString(arg) === '';
}

/*
* 공백 제거 포함, 배열,객체도 체크하기 위한 함수
* */
const isEmptyString = (arg) => {
  return _.isEmpty(arg) || _.trim(arg) === '';
}

const isValidId = (id) => _.isString(id) && idRegex.test(id);
const isValidPassword = (password) => _.isString(password) && passwordRegex.test(password);
export default {
  isEmpty,
  isEmptyString,
  isValidId,
  isValidPassword
}

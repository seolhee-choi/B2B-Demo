<template>
  <h1 class="form-title">판매 생성</h1>
  <div class="form-container">
    <div class="form-group">
      <label for="customerId">고객</label>
      <div class="form-inline">
        <input v-model="customerId" id="customerId" type="text" placeholder="고객 ID 또는 조회"  />
        <button type="button" @click="openCustomerDialog">조회</button>
      </div>
    </div>

    <div class="form-group">
      <label for="searchProductCode">상품 검색</label>
      <div class="form-inline">
        <input v-model="searchProductCodeKeyword" id="searchProductCode" type="text" placeholder="상품명 또는 코드 입력" />
        <button type="button" @click="openProductDialog">조회</button>
      </div>
    </div>

    <div class="form-group">
      <label>상품명</label>
      <input v-model="productName" readonly />
    </div>

    <div class="form-group">
      <label>상품코드</label>
      <input v-model="productCode" readonly />
    </div>

    <div class="form-group">
      <label>판매단가</label>
      <input v-model="unitPrice" type="number" readonly />
    </div>

    <div class="form-group">
      <label>할인금액</label>
      <input v-model="discount" type="number" readonly />
    </div>

    <div class="form-group">
      <label>판매금액</label>
      <input :value="saleAmount" readonly />
    </div>

    <div class="form-group">
      <label>배송요청일</label>
      <input v-model="deliveryDate" type="date" />
    </div>

    <div class="form-group">
      <label>배송지</label>
      <input v-model="shippingAddress" />
    </div>

    <div class="form-group">
      <label>등록일자</label>
      <div class="readonly">{{ createdAt }}</div>
    </div>

    <div class="form-actions">
      <button @click="submit">판매 등록</button>
    </div>
  </div>

  <!-- 상품 조회 모달 -->
  <SalesSearchDialog
    v-model="showProductDialog"
    :itemList="productList"
    :searchKeyword="searchProductCodeKeyword"
    :labelFields="['product_nm', 'product_cd']"
    :tableColumns="[
    { prop: 'product_cd', label: '상품코드' },
    { prop: 'product_nm', label: '상품명' },
    { prop: 'cost', label: '단가' },
    { prop: 'discount', label: '할인' }
  ]"
    @select="handleProductSelected"
  />


  <!-- 고객 조회 모달 -->
  <SalesSearchDialog
    v-model="showCustomerDialog"
    :itemList="customerList"
    :searchKeyword="searchCustomerKeyword"
    :labelFields="['custNm', 'custId']"
    :tableColumns="[
    { prop: 'custId', label: '고객 ID' },
    { prop: 'custNm', label: '고객명' }
  ]"
    @select="handleCustomerSelected"
  />


</template>

<script setup>
import { ref, computed } from "vue"
import SalesSearchDialog from "../salesMgmt/SalesSearchDialog.vue"

const customerId = ref("")
const searchCustomerKeyword = ref("")
const searchProductCodeKeyword = ref("")
const productName = ref("")
const productCode = ref("")
const unitPrice = ref(0)
const discount = ref(0)
const deliveryDate = ref("")
const createdAt = ref(new Date().toISOString().split("T")[0])
const shippingAddress = ref("")
const showCustomerDialog = ref(false)
const showProductDialog = ref(false)

const customerList = [
  { uid: "1", name: "홍길동1", tel: "010-1235-0001", post: "12345", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "101동1호" },
  { uid: "2", name: "박철수", tel: "010-1235-0002", post: "12346", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "102동2호" },
  { uid: "3", name: "김영희", tel: "010-1235-0003", post: "12343", addr1: "인천광역시 남동구 구월동 LG자이", addr2: "103동3호" },
]

const productList = [
  { uid: "1", product_nm: "냉장고", product_cd: "RF85DB9581A1A", cost: 100000, discount: 10000 },
  { uid: "2", product_nm: "세탁기", product_cd: "WM2025XY", cost: 80000, discount: 5000 },
  { uid: "3", product_nm: "전자레인지", product_cd: "MRX900", cost: 50000, discount: 2000 },
]

const openCustomerDialog = () => {
  searchCustomerKeyword.value = customerId.value.trim()
  showCustomerDialog.value = true
}

const openProductDialog = () => {
  // 항상 최근 값을 기준으로 초기화
  searchProductCodeKeyword.value = ""
  showProductDialog.value = true
}

const handleCustomerSelected = (customer) => {
  customerId.value = customer.custId
  showCustomerDialog.value = false
}

const handleProductSelected = (product) => {
  productName.value = product.product_nm
  productCode.value = product.product_cd
  unitPrice.value = product.cost
  discount.value = product.discount
  showProductDialog.value = false
}

const saleAmount = computed(() => unitPrice.value - discount.value)

const submit = async () => {
  try {
    const payload = {
      customerId: customerId.value,
      productCode: productCode.value,
      productName: productName.value,
      unitPrice: unitPrice.value,
      discount: discount.value,
      saleAmount: saleAmount.value,
      deliveryDate: deliveryDate.value,
      createdAt: createdAt.value,
      shippingAddress: shippingAddress.value,
    }

    const response = {

      data: {
        status: "success",
        message: "등록 성공",
        submittedData: payload,
      },
    }

    console.log("\ud83d\udce6 Response:", response.data)
    alert("판매 등록 완료")
  } catch (error) {
    console.error("\u274c 등록 실패:", error)
    alert("판매 등록 중 오류 발생")
  }
}
</script>

<style scoped>
.form-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 24px;
}

.form-container {
  max-width: 600px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 16px;
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 6px;
  font-weight: 500;
}

.form-group input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-group input[readonly] {
  background-color: #f5f5f5;
}

.form-inline {
  display: flex;
  gap: 8px;
}

.form-inline input {
  flex: 1;
}

.form-inline button {
  padding: 8px 12px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.form-inline button:hover {
  background-color: #0056b3;
}

.form-actions {
  text-align: right;
  margin-top: 24px;
}

.form-actions button {
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.form-actions button:hover {
  background-color: #218838;
}

.readonly {
  padding: 8px;
  background-color: #f5f5f5;
  border-radius: 4px;
  border: 1px solid #ccc;
}
</style>

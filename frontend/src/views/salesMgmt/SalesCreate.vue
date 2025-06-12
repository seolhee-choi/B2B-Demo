<script setup>
import { ref, computed } from "vue";

const customerId = ref("");
const searchProductCode = ref("");  // 상품코드 입력용
const productName = ref("");
const productCode = ref("");
const unitPrice = ref(0);
const discount = ref(0);
const deliveryDate = ref("");
const createdAt = ref(new Date().toISOString().split("T")[0]);
const shippingAddress = ref("");

const productList = [
  { uid: "1", product_nm: "냉장고", product_cd: "RF85DB9581A1A", cost: 100000, discount: 10000 },
  { uid: "2", product_nm: "세탁기", product_cd: "WM2025XY", cost: 80000, discount: 5000 },
  { uid: "3", product_nm: "전자레인지", product_cd: "MRX900", cost: 50000, discount: 2000 },
];

const saleAmount = computed(() => {
  return Math.max(0, unitPrice.value - discount.value);
});

const fetchProduct = () => {
  const product = productList.find(p => p.product_cd.toLowerCase() === searchProductCode.value.trim().toLowerCase());
  if (product) {
    productName.value = product.product_nm;
    productCode.value = product.product_cd;
    unitPrice.value = product.cost;
    discount.value = product.discount;
  } else {
    productName.value = "";
    productCode.value = "";
    unitPrice.value = 0;
    discount.value = 0;
  }
};

const submit = async () => {
  const data = {
    customerId: customerId.value,
    productName: productName.value,
    productCode: productCode.value,
    unitPrice: unitPrice.value,
    discount: discount.value,
    deliveryDate: deliveryDate.value,
    shippingAddress: shippingAddress.value,
  };
  console.log("제출할 데이터:", data);
  // 나중에 API 연결 예정
};
defineOptions({ name: 'SalesCreate' })
</script>

<template>
      <h1 class="form-title">판매 생성</h1>

      <div class="form-container">
        <div class="form-group">
          <label for="customerId">고객</label>
          <input v-model="customerId" id="customerId" type="text" placeholder="고객 ID 또는 조회" />
        </div>

        <div class="form-group">
          <label for="searchProductCode">상품코드 검색</label>
          <div class="form-inline">
            <input v-model="searchProductCode" id="searchProductCode" type="text" placeholder="상품코드 입력" />
            <button type="button" @click="fetchProduct">조회</button>
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
</template>

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

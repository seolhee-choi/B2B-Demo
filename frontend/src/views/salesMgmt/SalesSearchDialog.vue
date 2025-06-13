<template>
  <el-dialog v-model="visible" width="600px" title="검색" @close="handleClose">
    <div style="display: flex; gap: 8px; margin-bottom: 16px;">
      <el-input
        v-model="internalKeyword"
        placeholder="검색어 입력"
        clearable
        style="flex: 1"
      />
      <el-button type="success">검색</el-button>
    </div>

    <el-table
      :data="filteredList"
      style="width: 100%;"
      @row-dblclick="selectItem"
      highlight-current-row
      :empty-text="'검색 결과가 없습니다.'"
    >
      <el-table-column
        v-for="col in tableColumns"
        :key="col.prop"
        :prop="col.prop"
        :label="col.label"
      />
    </el-table>

    <template #footer>
      <el-button @click="handleClose">취소</el-button>
    </template>
  </el-dialog>
</template>
<script setup>
import { ref, computed, watch } from "vue"

const props = defineProps({
  modelValue: Boolean,
  itemList: {
    type: Array,
    default: () => [],
  },
  searchKeyword: {
    type: String,
    default: "",
  },
  labelFields: {
    // 필터링할 필드명 배열, ex: ['product_nm', 'product_cd']
    type: Array,
    default: () => [],
  },
  tableColumns: {
    // 보여줄 테이블 컬럼 정의 [{ prop: 'product_nm', label: '상품명' }, ...]
    type: Array,
    default: () => [],
  }
})

const emit = defineEmits(["update:modelValue", "select"])

const visible = ref(props.modelValue)
watch(() => props.modelValue, (val) => (visible.value = val))
watch(visible, (val) => {
  emit("update:modelValue", val)
})

// 내부 검색어
const internalKeyword = ref(props.searchKeyword)
watch(() => props.searchKeyword, (val) => (internalKeyword.value = val))

// 필터링 (labelFields 내 어느 필드라도 포함하면 통과)
const filteredList = computed(() => {
  const keyword = internalKeyword.value.trim().toLowerCase()
  if (!keyword) return props.itemList
  return props.itemList.filter((item) =>
    props.labelFields.some(
      (field) =>
        item[field]?.toString().toLowerCase().includes(keyword)
    )
  )
})

function selectItem(item) {
  emit("select", item)
  emit("update:modelValue", false)
}

function handleClose() {
  emit("update:modelValue", false)
}
</script>



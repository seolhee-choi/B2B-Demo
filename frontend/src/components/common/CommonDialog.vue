<template>
  <el-dialog
    :model-value="modelValue"
    :title="title"
    :width="width"
    @close="closeDialog"
  >
    <el-form
      ref="formRef"
      :model="formData"
      :rules="rules"
      label-width="100px"
    >
      <template v-for="field in fields" :key="field.name">
        <el-form-item :label="field.label" :prop="field.name">
          <!-- input -->
          <template v-if="field.type === 'text' || !field.type">
            <el-input
              v-model="formData[field.name]"
              :placeholder="field.placeholder"
              v-bind="field.props || {}"
              :class="{ 'readonly-fg': field.props?.readonly }"
            />
          </template>

          <!-- select -->
          <template v-else-if="field.type === 'select'">
            <el-select
              v-model="formData[field.name]"
              :placeholder="field.placeholder"
              v-bind="field.props || {}"
            >
              <el-option
                v-for="option in field.options"
                :key="option.value"
                :label="option.label"
                :value="option.value"
              />
            </el-select>
          </template>

          <!-- radio -->
          <template v-else-if="field.type === 'radio'">
            <el-radio-group v-model="formData[field.name]">
              <el-radio
                v-for="option in field.options"
                :key="option.value"
                :value="option.value"
              >
                {{ option.label }}
              </el-radio>
            </el-radio-group>
          </template>

          <!-- checkbox -->
          <template v-else-if="field.type === 'checkbox'">
            <el-checkbox-group v-model="formData[field.name]">
              <el-checkbox
                v-for="option in field.options"
                :key="option.value"
                :value="option.value"
              >
                {{ option.label }}
              </el-checkbox>
            </el-checkbox-group>
          </template>

          <!-- date -->
          <template v-else-if="field.type === 'date'">
            <el-date-picker
              v-model="formData[field.name]"
              type="date"
              :placeholder="field.placeholder || '날짜 선택'"
              v-bind="field.props || {}"
            />
          </template>

          <!-- textarea -->
          <template v-else-if="field.type === 'textarea'">
            <el-input
              type="textarea"
              v-model="formData[field.name]"
              :placeholder="field.placeholder"
              v-bind="field.props || {}"
            />
          </template>
        </el-form-item>
      </template>

      <!-- 템플릿 슬롯 영역 추가 -->
      <slot name="addTemplate" />
    </el-form>

    <template #footer>
      <el-button @click="closeDialog">{{ cancelButtonTitle }}</el-button>
      <el-button type="primary" @click="submitForm">{{ okButtonTitle }}</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive, watch, ref } from 'vue'

const props = defineProps({
  modelValue: Boolean,
  title: String,
  okButtonTitle: String,
  cancelButtonTitle: String,
  width: {
    type: String,
    default: '500px'
  },
  fields: {
    type: Array,
    default: () => []
  },
  rules: {
    type: Object,
    default: () => ({})
  },
  initialData: {
    type: Object,
    default: () => ({})
  }
})

const emit = defineEmits(['update:modelValue', 'submit'])

const formRef = ref(null)
const formData = reactive({})

// 초기값 설정
watch(
  () => props.fields,
  () => {
    props.fields.forEach(field => {
      formData[field.name] = props.initialData[field.name] ?? ''
    })
  },
  { immediate: true }
)

// 닫기 처리
function closeDialog() {
  emit('update:modelValue', false)
}

// 확인 버튼 클릭
function submitForm() {
  formRef.value.validate(valid => {
    if (valid) {
      emit('submit', { ...formData })
      closeDialog()
    }
  })
}
</script>

<style scoped>
.readonly-fg ::v-deep(.el-input__wrapper) {
  background-color: #f5f5f5;
}
</style>

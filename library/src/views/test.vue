<template>
  <el-card class="userinfo-card">
    <template #header>
      <div class="card-header">
        <span>{{ $t('message.text') }}</span>
        <el-select v-model="selectValue" @change="langChange" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>

      </div>
    </template>
    <el-form class="userinfo-box" label-width="auto" size="large">
      <el-form-item label="用户名:"> username </el-form-item>
      <el-form-item label="其他信息:"> other </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      selectValue: '',
      options: [
        {
          value: 'zhcn',
          label: '中文'
        }, {
          value: 'en',
          label: 'English'
        }
      ]
    }
  },
  created() {
    let that = this;
    console.log(localStorage.lang)
    that.selectValue = localStorage.lang == undefined ? 'cn' : localStorage.lang
  },
  methods: {
    //语言切换
    langChange(e) {
      console.log(e)
      localStorage.setItem('lang', e);
      this.$i18n.locale = e;
    }
  },

}
</script>

<style scoped></style>

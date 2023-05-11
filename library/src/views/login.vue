<template>
  <div class="login-container">
    <el-form :model="admin" status-icon :rules="rules" ref="admin" label-width="100px" class="login-form">
      <div style="padding-bottom: 30px;">
        <span class="title-zc">{{ $t('login.page') }}</span>
        <i class="el-icon-setting"></i>
        <el-select v-model="selectValue" @change="langChange" placeholder="selectValue" class="title-sel">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </div>
      <el-form-item :label="$t('login.account')" prop="username">
        <el-input v-model.number="admin.username"></el-input>
      </el-form-item>

      <el-form-item :label="$t('login.password')" prop="password">
        <el-input type="password" v-model="admin.password" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submit(admin)">{{ $t('login.login') }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      admin: {
        username: "",
        password: "",
      },
      rules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入昵称", trigger: "blur" }],
      },
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
    };
  },
  created() {
    let that = this;
    console.log(localStorage.lang)
    that.selectValue = localStorage.lang == undefined ? 'cn' : localStorage.lang
  },
  methods: {
    submit(admin) {
      this.$axios.post("/admins/login", admin).then((res) => {
        if (res.data == "ok") {
          this.$router.push({ name: 'home', params: { admin: admin } });
        } else {
          this.$message(res.data);
        }
      });
    },
    //语言切换
    langChange(e) {
      localStorage.setItem('lang', e);
      this.$i18n.locale = e;
    }
  },
};
</script>
<style scoped>
.login-container {
  position: absolute;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background-image: url("../assets/bg.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}

.login-form {
  width: 350px;
  margin: 150px auto;
  background-color: rgba(90, 187, 211, 0.7);
  padding: 30px;
  border-radius: 20px;
}

.title-zc {
  margin-left: 20%;
  margin-right: 13%;
  font-size: 30px;
  font-weight: bold;
}

.title-sel {
  width: 80px;
}
</style>
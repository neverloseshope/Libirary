<template>
  <div id="borrowBook">
    <!-- 1.面包屑 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">{{$t('home.page')}}</el-breadcrumb-item>
      <el-breadcrumb-item>{{$t('home.borrow')}}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form ref="borrowInfo" :model="borrowInfo" label-width="80px">
      <el-form-item hidden>
        <el-input v-model="borrowInfo.bbId"></el-input>
      </el-form-item>
      <el-form-item :label="$t('main.borrower')">
        <el-select v-model="borrowInfo.borrowerId">
          <el-option
            v-for="borrower in borrowerList"
            :label="borrower.name"
            :value="borrower.id"
            :key="borrower.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('main.book')">
        <el-select v-model="borrowInfo.bookId">
          <el-option
            v-for="book in bookList"
            :label="book.name"
            :value="book.id"
            :key="book.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('main.borrowTime')">
        <el-date-picker
          type="date"
          :placeholder="$t('message.pickTime')"
          v-model="borrowInfo.borrowDate"
          style="width: 100%"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item :label="$t('main.returnTime')">
        <el-date-picker
          type="date"
          :placeholder="$t('message.pickTime')"
          v-model="borrowInfo.returnDate"
          style="width: 100%"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit(borrowInfo)">{{$t('main.submit')}}</el-button>
        <el-button type="primary" @click="reset()">{{$t('main.reset')}}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      borrowInfo: {
        bbId: "",
        borrowerId: "",
        bookId: "",
        borrowDate: "",
        returnDate: "",
      },
      borrowerList: [],
      bookList: [],
    };
  },
  created() {
    this.selectBorrowerList();
    this.selectBookList();
  },
  methods: {
    async selectBorrowerList() {
      const res = await this.$axios.get("/borrower/allBorrower");
      this.borrowerList = res.data;
    },
    async selectBookList() {
      const res = await this.$axios.get("/book/allBook");
      this.bookList = res.data;
    },
    async clearBorrowInfo() {
      this.borrowInfo = {
        bbId: "",
        borrowerId: "",
        bookId: "",
        borrowDate: "",
        returnDate: "",
      };
    },
    submit(borrowInfo) {
      if (
        borrowInfo.borrowerId == "" ||
        borrowInfo.bookId == "" ||
        borrowInfo.borrowDate == "" ||
        borrowInfo.returnDate == ""
      ) {
        this.$message(this.$t('message.incomplete'));
      } else if (borrowInfo.borrowDate < borrowInfo.returnDate) {
        this.addBorrowBook(borrowInfo);
      } else {
        this.$message(this.$t('message.wrongTime'));
      }
    },
    async addBorrowBook(borrowInfo) {
      const res = await this.$axios.post(
        "/borrowerBook/addBorrowerBook",
        borrowInfo
      );
      if (res.status != 200) {
        this.$message(this.$t('message.borrowFail'));
      }
      this.$message(this.$t('message.borrowSuccess'));
      this.clearBorrowInfo();
    },
    reset() {
      this.clearBorrowInfo();
    },
  },
};
</script>

<style scoped>
</style>

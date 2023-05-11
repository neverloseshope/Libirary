<template>
  <div id="returnBook">
    <!-- 1.面包屑 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">{{ $t('home.page') }}</el-breadcrumb-item>
      <el-breadcrumb-item>{{ $t('main.returnBook') }}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table :data="borrowerBookList" stripe style="width: 100%" ref="multipleTable" :header-cell-style="{
      height: '60px',
      color: '#859dbd',
      padding: '0 0 0 20px',
    }">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column :label="$t('main.borrowNo')" width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.bbId }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('main.borrower')" width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.borrower.name }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('main.book')" width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.book.name }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('main.borrowTime')" width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.borrowDate }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('main.returnTime')" width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.returnDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row.bbId)" icon="el-icon-edit">
            {{ $t('main.editInfo') }}
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.bbId)" icon="el-icon-delete">
            {{ $t('main.clickReturn') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="tool">
      <el-button type="primary" @click="handleDeleteList()"><i class="el-icon-upload el-icon--right"></i>{{
        $t('main.batchReturn') }}</el-button>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
        :page-sizes="[3, 5, 10]" :page-size="5" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <!--修改对话框 -->
    <el-dialog :title="$t('main.editInfo')" :visible.sync="dialogVisible" width="30%">
      <el-form ref="borrowerBook" :model="borrowerBook" label-width="80px">
        <el-form-item hidden>
          <el-input v-model="borrowerBook.bbId"></el-input>
        </el-form-item>
        <el-form-item :label="$t('main.borrower')">
          <el-select v-model="borrowerBook.borrower.id">
            <el-option v-for="borrower in borrowerList" :label="borrower.name" :value="borrower.id"
              :key="borrower.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('main.book')">
          <el-select v-model="borrowerBook.book.id">
            <el-option v-for="book in bookList" :label="book.name" :value="book.id" :key="book.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('main.borrowTime')">
          <el-date-picker type="date" :placeholder="$t('message.pickTime')" v-model="borrowerBook.borrowDate"
            style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item :label="$t('main.returnTime')">
          <el-date-picker type="date" :placeholder="$t('message.pickTime')" v-model="borrowerBook.returnDate"
            style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateBorrowerBook(borrowerBook)">
            {{ $t('main.edit') }}
          </el-button>
          <el-button @click="dialogVisible = false">{{ $t('main.cancel') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      borrowerBookList: [],
      borrowerBook: {
        bbId: "",
        borrower: {
          id: "",
          name: "",
        },
        book: {
          id: "",
          name: "",
        },
        borrowDate: "",
        returnDate: "",
      },
      currentPage: 1, //当前页
      pageSize: 5, //每页多少条记录
      total: 0, //总记录数
      pages: 0, //总页数
      dialogVisible: false,
      borrowerList: [],
      bookList: [],
    };
  },
  created() {
    this.selectList();
    this.selectBookList();
    this.selectBorrowerList();
  },
  methods: {
    async selectList() {
      const res = await this.$axios.get("/borrowerBook/selectPageList", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
        },
      });
      if (res.status != 200) {
        this.$message(this.$t('message.getDataFail'));
        return;
      }
      this.borrowerBookList = res.data.records;
      this.total = res.data.total;
      this.pages = res.data.pages;
    },
    handleSizeChange(newPageSize) {
      this.pageSize = newPageSize;
      this.selectList();
    },
    handleCurrentChange(newCurrentPage) {
      this.currentPage = newCurrentPage;
      this.selectList();
    },
    //修改
    handleEdit(bbId) {
      this.selectBorrowerBook(bbId);
      this.dialogVisible = true;
    },
    async selectBorrowerBook(bbId) {
      const res = await this.$axios.get("/borrowerBook/queryById?bbId=" + bbId);
      if (res.status != 200) {
        this.$message(this.$t('message.getDataFail'));
        return;
      }
      this.borrowerBook = res.data;
    },
    async updateBorrowerBook(borrowerBook) {
      const res = await this.$axios.post(
        "/borrowerBook/updateBorrowerBook",
        borrowerBook
      );
      if (res.status != 200) {
        this.$message(this.$t('message.editFail'));
        return;
      }
      this.$message(this.$t('message.editFail'));
      this.dialogVisible = false;
      this.clearBorrowerBook();
      this.selectList();
    },
    //单个删除
    handleDelete(bbId) {
      this.deleteBorrowerBook(bbId);
    },
    async deleteBorrowerBook(bbId) {
      const res = await this.$axios.get(
        "/borrowerBook/deleteById?bbId=" + bbId
      );
      if (res.status != 200) {
        this.$message(this.$t('message.returnFail'));
        return;
      }
      this.$message(this.$t('message.returnSuccess'));
      if (
        this.total % this.pageSize == 1 &&
        this.currentPage != 1 &&
        this.currentPage == this.pages
      ) {
        this.currentPage--;
      }
      this.selectList();
    },
    // 批量删除
    handleDeleteList() {
      this.$confirm(this.$t('message.deleteWarn'), this.$t('message.tip'), {
        confirmButtonText: this.$t('main.ok'),
        cancelButtonText: this.$t('main.cancel'),
        type: "warning",
      })
        .then(() => {
          var bbIdList = [];
          var sel = this.$refs.multipleTable.selection;
          sel.map((item) => bbIdList.push(item.bbId));
          this.deleteList(bbIdList);
        })
        .catch(() => {
          this.$message(this.$t('message.cancelDel'));
        });
    },
    async deleteList(bbIdList) {
      const res = await this.$axios.post("/borrowerBook/deleteList", bbIdList);
      if (res.status != 200) {
        this.$message(this.$t('message.returnFail'));
        return;
      }
      this.$message(this.$t('message.returnSuccess'));
      if (
        (bbIdList.length == this.pageSize ||
          (this.currentPage == this.pages &&
            bbIdList.length == this.total % this.pageSize)) &&
        this.currentPage != 1
      ) {
        this.currentPage--;
      }
      this.selectList();
    },
    //恢复默认值
    clearBorrowerBook() {
      this.borrowerBook = {
        bbId: "",
        borrower: {
          id: "",
          name: "",
        },
        book: {
          id: "",
          name: "",
        },
        borrowDate: "",
        returnDate: "",
      };
    },
    async selectBorrowerList() {
      const res = await this.$axios.get("/borrower/allBorrower");
      this.borrowerList = res.data;
    },
    async selectBookList() {
      const res = await this.$axios.get("/book/allBook");
      this.bookList = res.data;
    },
  },
};
</script>

<style>
.el-table {
  margin-top: 10px;
}

.tool {
  position: relative;
  display: flex;
  width: 100%;
  height: 40px;
  margin-top: 10px;
}

.tool .el-pagination {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>
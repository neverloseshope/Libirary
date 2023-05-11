import zhLocale from 'element-ui/lib/locale/lang/zh-CN'

const zhcn = {
  login: {
    page: '登录页',
    account: '账号',
    password: '密码',
    login: '登录',
  },
  home: {
    title: '图书管理系统',
    page: '首页',
    borrow: '借书',
    return: '还书',
    book: '书籍管理',
    borrower: '借阅人管理',
    publisher: '出版社管理',
  },
  main: {
    submit: '提交',
    reset: '重置',
    operate:'操作',
    edit:'修改',
    ok: '确定',
    cancel:'取消',
    borrower: '借阅人',
    book: '书籍',
    borrowTime: '借阅时间',
    returnTime: '归还时间',
    returnBook:'还书',
    borrowNo:'借阅编号',
    editInfo:'修改信息',
    clickReturn:'一键还书',
    batchReturn:'批量还书',

  },
  message: {
    pickTime: '选择日期',
    incomplete: '请将信息填写完整!',
    wrongTime: '还书时间不能小于借书时间',
    borrowFail: '借书失败',
    borrowSuccess: '借书成功',
    getDataFail:'获取数据失败',
    editFail:'修改失败',
    returnFail: '还书失败',
    returnSuccess: '还书成功',
    deleteWarn:'确定要删除这些数据吗?',
    tip: '提示',
    cancelDel:'已取消删除',
  },
  ...zhLocale
}

export default zhcn;

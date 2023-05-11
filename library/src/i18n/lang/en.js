import enLocale from 'element-ui/lib/locale/lang/en'

const en = {
  login: {
    page: 'LoginPage',
    account: 'Account',
    password: 'Password',
    login: 'Login',
  },
  home: {
    title: 'LibirarySystem',
    page: 'home',
    borrow: 'BorrowBook',
    return: 'ReturnBook',
    book: 'BookManager',
    borrower: 'BorrowerManager',
    publisher: 'PublisherManager',
  },
  main:{
    submit: 'Submit',
    reset: 'Reset',
    operate:'operate',
    edit:'modify',
    ok: 'ok',
    cancel:'Cancel',
    borrower: 'Borrower',
    book: 'Book',
    borrowTime: 'BorrowTime',
    returnTime: 'ReturnTime',
    returnBook:'ReturnBook',
    borrowNo:'BorrowNumber',
    editInfo:'ModifyInformation',
    clickReturn:'ReturnBook',
    batchReturn:'BatchReturn',
  },
  message: {
    pickTime: 'Select time',
    incomplete: 'Please fill in the information completely!',
    wrongTime: 'The return time cannot be less than the borrowing time!',
    borrowFail: 'Borrowing failed',
    borrowSuccess: 'Successfully borrowed',
    getDataFail:'Failed to obtain data',
    editFail:'Modification failed',
    returnFail: 'Return failed',
    returnSuccess: 'Successfully returned',
    deleteWarn:'Are you sure you want to delete these data?',
    tip: 'prompt',
    cancelDel:'Cancelled deletion',
  },
  ...enLocale
}

export default en;

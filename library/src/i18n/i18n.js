// import Vue from 'vue'
// import VueI18n from 'vue-i18n'

// Vue.use(VueI18n)

// function loadLocaleMessages () {
//   const locales = require.context('./i18n', true, /[A-Za-z0-9-_,\s]+\.json$/i)
//   const messages = {}
//   locales.keys().forEach(key => {
//     const matched = key.match(/([A-Za-z0-9-_]+)\./i)
//     if (matched && matched.length > 1) {
//       const locale = matched[1]
//       messages[locale] = locales(key)
//     }
//   })
//   return messages
// }

// export default new VueI18n({
//   locale: process.env.VUE_APP_I18N_LOCALE || 'en',
//   fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || 'en',
//   messages: loadLocaleMessages()
// })

import Vue from 'vue'
import locale from 'element-ui/lib/locale';
import VueI18n from 'vue-i18n'
import messages from './lang'
Vue.use(VueI18n)
//从localStorage获取语言选择。
const i18n = new VueI18n({
    locale: localStorage.lang || 'zhcn', //初始未选择默认 zhcn 中文
    messages
})
locale.i18n((key, value) => i18n.t(key, value)) //兼容element
 
export default i18n

// java script entry point

require('./bootstrap');

import Vue from 'vue';
import StarRating from 'vue-star-rating'
Vue.component('star-rating', StarRating);

const app = new Vue({
  el: "#app",
    data: {
     message: 'このメッセージはVue.jsで出力されています'
    },
    
  el: '#star',
    data: {
      rating: 0
    }
})

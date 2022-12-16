<template>
  <div class="textarea-wrap">
    <div
        autocapitalize="off"
        class="mTextarea"
        ref='mTextarea'
        @input="input"
        :style="style"
        contenteditable="true"
        :placeholder="placeholder"
    ></div>
  </div>
</template>
<script>
export default {
  props: {
    // 输入提示，非必传
    placeholder: {
      type: String,
      default: '请输入发布内容'
    },

    // 最大字数，非必传
    maxlength: {
      type: Number,
      default: 99999
    },
    // 行数，非必传
    rows: {
      type: Number,
      default: 6
    },
    // 字体大小，非必传
    fontSize: {
      type: Number,
      default: 30
    },
    // 行高，非必传
    lineHeight: {
      type: Number,
      default: 1.5
    },
    // 值，必传
    value: {
      type: String,
      default: ''
    }
  },
  computed: {
    style () {
      return {
        fontSize: this.fontSize / 25 + 'rem',
        lineHeight: this.lineHeight,
        maxHeight: this.rows * this.fontSize / 25 * this.lineHeight + 'rem',
      }
    }
  },
  data () {
    return {
      mTextarea: ''
    }
  },
  watch: {
    value () {
      // 清空
      if (!this.value) {
        this.mTextarea.innerText = ''
      }
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.mTextarea = this.$refs.mTextarea
      // 回显
      this.mTextarea.innerText = this.value
    })
  },
  methods: {
    input () {
      let innerText = this.mTextarea.innerText
      // 限制输入
      if (innerText.length >= this.maxlength) {
        innerText = innerText.substring(0, this.maxlength)
        this.mTextarea.innerText = innerText
        // 设置光标到最后
        this.setFocus()
      }
      if (this.mTextarea.innerText.length === 1 && this.mTextarea.innerHTML.includes('br')) {
        this.mTextarea.innerText = ''
        innerText = ''
      }
      this.$emit('input', innerText)
    },
    // 设置光标到最后
    setFocus () {
      let selection = window.getSelection()
      let range = document.createRange()
      range.selectNodeContents(this.mTextarea)
      range.collapse(false)
      selection.removeAllRanges()
      selection.addRange(range)
    }
  }
}
</script>
<style lang="scss" scoped>
.textarea-wrap{
  background: #f8f8f8;;
  padding: 24/75rem;
  border-radius: 2px;
  font-size: 0;
  &:active{
    background: #eee;
  }
  .mTextarea{
    resize: none;
    padding: 8px;
    border-radius: 4px;
    border: none;

    outline: none;
    width: 100%;
    &[disabled] {
      box-shadow: 0px 0px 3px 0px gray;
    }
    display: inline-block;

    overflow: auto;
    // 加了可实现纯textarea功能，不加则像富文本一样可以写入css样式和HTML标签
    user-modify: read-write-plaintext-only;
    -webkit-user-modify: read-write-plaintext-only;
    // 必须加，不然移动端有些浏览器无光标
    user-select: auto;
    -webkit-user-select: auto;
    // placeholder属性
    &:empty:before{
      content:attr(placeholder);
      font-size: 28/75rem;
      color: #999;
    }
  }
}
</style>


>
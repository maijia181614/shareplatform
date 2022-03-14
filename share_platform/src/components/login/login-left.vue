<template>
<div class="login-left-div">
  <div class="cover">
    <img src="../../static/images/DOGE.png">
  </div>
  <div class="login-left-border">
  </div>
</div>

</template>

<script>
export default {
name: "login-left"
}
</script>

<style scoped>
div.login-left-div{
  display: flex;
  flex-direction: row;
  align-items: center;
}
div.login-left-border{
  border-right-width: 2px;
  border-right-color: #F2F3F5;
  border-right-style: solid;
  height: 200px;
  margin-right: 50px;
  margin-left: 200px;
}
div.cover{
  position: relative;
}
div.cover:after{
  position: absolute;
  content: '';
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  box-shadow:0 0 50px 30px #ffffff inset;
}
</style>

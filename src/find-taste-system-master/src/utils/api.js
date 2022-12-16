import network from './network';

// 登录
export function login(data) {
  return network({
    url: `/login`,
    method: "post",
    data
  });
}

// 注册
export function register(data) {
  return network({
    url: `/register`,
    method: "post",
    data
  })
}

// 密码重置
export function resetPwd(data) {
  return network({
    url: `/resetPwd`,
    method: "post",
    data
  })
}

// 任务列表
export function getInfo(params) {
  return network({
    url: `/getInfo`,
    method: "get",
    params,
  })
}

//发布者发起
export function getPostList(params) {
  return network({
    url: `/getPostList`,
    method: "get",
    params,
  })
}

//响应着发起
export function findPostList(params) {
  return network({
    url: `/getPostList`,
    method: "get",
    params,
  })
}

export function foundPostList(params) {
  return network({
    url: `/getFoundPost`,
    method: "get",
    params,
  })
}

export function getRespond(data) {
  return network({
    url: `/getRespond`,
    method: "post",
    data,
  })
}

// 编辑任务
export function editNumber(data) {
console.log(data)
  return network({
    url: `/editNumber`,
    method: "post",
    data
  })
}

// 删除post
export function deletePost(params) {
  return network({
    url: `/deletePost`,
    method: "put",
    params
  })
}

//
export function newPost(params) {
  return network({
    url: `/newPost`,
    method: "post",
    params
  })
}

// 删除任务
export function deleteRespond(data) {
  return network({
    url: `/deleteRespond`,
    method: "post",
    data
  })
}

export function acceptRespond(data) {
  return network({
    url: `/acceptRespond`,
    method: "post",
    data
  })
}
export function modifyRespond(data){
  return network({
    url: `/modifyRespond`,
    method: "post",
    data
  })
}

export function newComment(data) {
  return network({
    url: `/newComment`,
    method: "post",
    data
  })
}
export function postChart(data) {
  return network({
    url: `/getChart`,
    method: "post",
    data
  })
}

export function submitUp(data){
  return network({
    url: `/submit`,
    method: "post",
    data
  })
}


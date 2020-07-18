# Vue组件

## Vue父子组件交互

### 子组件修改`props`

[.sync 修饰符](https://cn.vuejs.org/v2/guide/components-custom-events.html#sync-%E4%BF%AE%E9%A5%B0%E7%AC%A6)

### Vue子组件访问`props`中的数据

```vue
props: {
  form: {
    type: Object,
    default() {
      return {}
    }
  }
},
watch: {
  'form': function(form) {
    this.pId = [form.parentId]
  }
}
```

## ElementUI小技巧

### 清空ElementUI `form` 表单校验状态

```vue
if (this.$refs['form'] !== undefined) {
    this.$refs['form'].resetFields()
}
```

### ElementUI Tree组件多选改为单选

ElementUI Tree组件官方文档：[https://element.eleme.cn/#/zh-CN/component/tree](https://element.eleme.cn/#/zh-CN/component/tree)

从文档中可以看到，官方提供的Tree组件有个问题：

- 开启`show-checkbox`选项，则`tree`组件展示`checkbox`，但此时组件已经支持多选了
- 不开启`show-checkbox`，组件默认支持单选，节点是否选中根据此节点颜色判断。

如果你和我一样觉得这是很坑的（实现`tree`组件既要显示`checkbox`，又要是单选的），那么请遵循以下方式：

1. 页面`<el-tree>`

```html
<el-tree
         ref="tree"
         v-model="form.deptId"
         :data="deptTree"
         highlight-current
         show-checkbox
         check-strictly
         :default-checked-keys="deptId"
         :default-expanded-keys="deptId"
         node-key="id"
         :props="treeProps"
         @check-change="checkChange"
/>
```

2. JS代码

```vue
checkChange(data, node, self) {
    if (node) {
      this.pId = [data.id]
      this.$refs.tree.setCheckedKeys(this.pId)
      this.form.parentId = data.id
    } else {
      if (this.$refs.tree.getCheckedKeys().length == 0) {
        this.pId = []
        this.form.parentId = null
      }
    }
}
```

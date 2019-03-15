# TokenCoin和Account类问题汇总

## TokenCoin类不支持ERC20标准

1. 不支持设置代币简称(symbol)
2. 不支持自定义设置小数点后几位(decimall)

## Account类找不到根据地址转账、获取余额等方法
1. 参考ERC20的transfer, approve等转账方法

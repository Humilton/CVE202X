import time
import frida

# 连接安卓机上的frida-server
device = frida.get_usb_device()
# 启动`demo02`这个app
pid = device.spawn(["com.cve20xx"])
device.resume(pid)
time.sleep(1)
session = device.attach(pid)
# 加载s1.js脚本
with open("frida_sample.js") as f:
    script = session.create_script(f.read())

def my_message_handler(message , payload): #定义错误处理
    print(message)
    print(payload)

script.on("message" , my_message_handler) #调用错误处理
script.load()

# 脚本会持续运行等待输入
#raw_input()
input()
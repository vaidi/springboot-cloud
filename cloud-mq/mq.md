name: 交换机名称
type: 交换机类型 direct，topic，fanout，headers
durability: 是否需要持久化，true 为持久化
auto delete: 当最后一个绑定到 exchange 上的队列被删除后，exchange 没有绑定的队列了，自动删除该 exchange
internal: 当前 exchange 是否用于 rabbitMQ 内部使用，默认为 false
arguments: 扩展参数，用于扩展 AMQP 协议自制定化使用
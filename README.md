# BudgetBackend
The Backend Project about the IOS Budget Application


# AccountSer
  Account Service,is used to bind/create account of IOS App.This service use SpringSecurity as the low level framework.

# BudgetSer
  Budget Service, is the http server to record the expenditure,also it can respond record lists,swift chart datas and input recommendation data.This service use Postgresql 12 as Relation Database and use Redis as Postgresql's Cache.Meanwhile,use Nacos as the Dynamic Configuration.

# QuartzSer
  Quartz Service is the Scheduler that be used to integrate Hadoop generate Recommendation about the Spend Name.

# QueueSer
  Queue Service integrate Kafka as the Message Bus.

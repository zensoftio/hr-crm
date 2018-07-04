import pika


class Receive:
    connection = pika.BlockingConnection(pika.URLParameters('amqp://mqadmin:mqadminpassword@192.168.89.113:5672'))
    channel = connection.channel()

    channel.exchange_declare(exchange='exchangeForTemplate',
                             exchange_type='direct')

    result = channel.queue_declare(exclusive=True)
    queue_name = result.method.queue

    channel.queue_bind(exchange='exchangeForTemplate',
                       queue='template3')

    print(' [*] Waiting for logs. To exit press CTRL+C')

    def callback(ch, method, properties, body):
        print(" [x] %r:%r" % (method.routing_key, body))

    channel.basic_consume(callback,
                          queue='template3',
                          no_ack=True)

    channel.start_consuming()
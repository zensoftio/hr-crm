# !/usr/bin/env python
import json

import pika

parameters = pika.URLParameters('amqp://guest:guest@localhost:5672')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.exchange_declare(exchange='candidates',
                         exchange_type='direct')

message = json.dumps(
    [
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'invitations@linkedin.com'},
        {'email': 'notifications@github.com'},
        {'email': 'email@email.prezi.com'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'do-not-reply@trello.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'no-reply@accounts.google.com'},
        {'email': 'info@twitter.com'},
        {'email': 'noreply@e.geekbrains.ru'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'editor@email.netology.ru'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'taco@trello.com'},
        {'email': 'notifications@github.com'},
        {'email': 'CloudPlatform-noreply@google.com'},
        {'email': 'no-reply@mail.instagram.com'},
        {'email': 'info@ubegin.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'groups-noreply@linkedin.com'},
        {'email': 'digest-noreply@quora.com'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'info@twitter.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'info@twitter.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'no-reply@accounts.google.com'},
        {'email': 'notifications@github.com'},
        {'email': 'no-reply@mail.instagram.com'},
        {'email': 'info@twitter.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'diezeit@newsletterversand.zeit.de'},
        {'email': 'no-reply@mail.instagram.com'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'sales@trioangle.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'info@twitter.com'},
        {'email': 'digest-noreply@quora.com'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'messages-noreply@linkedin.com'},
        {'email': 'notifications@github.com'},
        {'email': 'CloudPlatform-noreply@google.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'info@twitter.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'info@ubegin.com'},
        {'email': 'cloudplatform-noreply@google.com'},
        {'email': 'digest-noreply@quora.com'},
        {'email': 'notifications@github.com'},
        {'email': 'do-not-reply@trello.com'},
        {'email': 'subscribe@ru.jooble.org'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'messages-noreply@linkedin.com'},
        {'email': 'do-not-reply@trello.com'},
        {'email': 'notifications@github.com'},
        {'email': 'support@freelance.kg'},
        {'email': 'noreply@e.geekbrains.ru'},
        {'email': 'notifications@github.com'},
        {'email': 'notify@twitter.com'},
        {'email': 'no-reply@easybacklog.com'},
        {'email': 'mailer-daemon@googlemail.com'},
        {'email': 'groups-noreply@linkedin.com'},
        {'email': 'notifications@github.com'},
        {'email': 'notifications@github.com'},
        {'email': 'tynarbekov95@gmail.com',
         'url': [
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-decoded (3).png-Thu Jul 12 2018 17:20:16 GMT+0600 (+06)']},
        {'email': 'tynarbekov95@gmail.com',
         'url': [
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-base64-Thu Jul 12 2018 17:20:17 GMT+0600 (+06)']},
        {'email': 'tynarbekov95@gmail.com',
         'url': [
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-decoded (1).png-Thu Jul 12 2018 17:20:18 GMT+0600 (+06)',
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-fail.png-Thu Jul 12 2018 17:20:18 GMT+0600 (+06)',
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-ik.sql-Thu Jul 12 2018 17:20:18 GMT+0600 (+06)']},
        {'email': 'tynarbekov95@gmail.com',
         'url': [
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-iaau.png-Thu Jul 12 2018 17:20:19 GMT+0600 (+06)',
             'https://storage.googleapis.com/candidate-cvs/tynarbekov95@gmail.com-base64-Thu Jul 12 2018 17:20:19 GMT+0600 (+06)']}
    ]

)

channel.basic_publish(exchange='candidates', routing_key='candidate', body=message,
                      properties=pika.BasicProperties(delivery_mode=2))

print(" [x] Sent %r:%r" % ('success', message))
connection.close()

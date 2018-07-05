from fcm_django.models import FCMDevice

message = {
    'title': 'New Candidate',
    'body': "New candidate has been created"
}


def candidate_created(sender, **kwargs):
    if kwargs['created']:
        devices = FCMDevice.objects.all()
        devices.send_message(**message)
        print("NEW CANDIDATE")

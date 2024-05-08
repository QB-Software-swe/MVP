from locust import HttpUser, task, constant
import json
import random


class Bobr(HttpUser):

    wait_time = constant(0.5)

    name = "bobr"
    password = ""
    email = "team@qbsoftware.swe.it"

    emailPool = ["bobrTest@qbsoftware.it", "bobrTry@qbsoftware.it", "bobrTrial@qbsoftware.it"]

    accountId = ""
    mbSentId = ""
    counter = 0

    with open("locust/requestExamples/show_mailboxes.json", "r") as aux:
        show_mb = aux.read()
    with open("locust/requestExamples/create_mailbox_sent.json", "r") as aux:
        create_sent_mb = aux.read()
    with open("locust/requestExamples/populateMB.json", "r") as aux:
        genMB = aux.read()
    with open("locust/requestExamples/send_email.json", "r") as aux:
        send_email_original = aux.read()

    def on_start(self):
        # Auth
        auth_response = self.client.get("/.well-known/jmap", auth=(self.email, self.password)).text
        auth_response = json.loads(auth_response)

        # Get accountId
        self.accountId = list(auth_response["accounts"].keys())[0]

        # Generate mailboxes
        self.client.post("/api", auth=(self.email, self.password), data=self.genMB.replace("Id_here", self.accountId))

        # Create mailbox_sent
        self.show_mb = self.show_mb.replace("Id_here", self.accountId)
        show_mb_response = self.client.post("/api", auth=(self.email, self.password), data=self.show_mb).text
        show_mb_response = json.loads(show_mb_response)
        mb_list = show_mb_response["methodResponses"][0][1]["list"]
        isPresent = False
        for mb in mb_list:
            if mb["role"] == "sent":
                isPresent = True
                self.mbSentId = mb["id"]
                break
        if not isPresent:
            self.create_sent_mb = self.create_sent_mb.replace("Id_here", self.accountId)
            create_sent_mb_response = self.client.post("/api", auth=(self.email, self.password), data=self.create_sent_mb).text
            create_sent_mb_response = json.loads(create_sent_mb_response)
            self.mbSentId = create_sent_mb_response["methodResponses"][0][1]["created"]["sent-example"]["id"]

    @task
    def send_test(self):
        send_email = self.send_email_original

        send_email = send_email.replace("Id_here", self.accountId)
        send_email = send_email.replace("email_here", random.choice(self.emailPool))
        send_email = send_email.replace("counter_here", str(self.counter))
        send_email = send_email.replace("Id_mail_here", "Test"+str(self.counter))
        send_email = send_email.replace("mb_Id_here", self.mbSentId)

        self.client.post("/api", auth=(self.email, self.password), data=send_email)

        self.counter += 1

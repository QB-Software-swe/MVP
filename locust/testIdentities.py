from locust import HttpUser, task, constant
import json
import random


class Karen(HttpUser):

    wait_time = constant(0.5)

    name = "karen"
    password = ""
    email = "team@qbsoftware.swe.it"

    emailPool = ["karentest@qbsoftware.it", "karentry@qbsoftware.it", "karenisreal@qbsoftware.it"]
    namePool = ["karenTest", "karenTry", "karenTrial"]

    accountId = ""
    mbSentId = ""
    counter = 0

    with open("locust/requestExamples/genIdentity.json", "r") as aux:
        genIdentity = aux.read()

    def on_start(self):
        # Auth
        auth_response = self.client.get("/.well-known/jmap", auth=(self.email, self.password)).text
        auth_response = json.loads(auth_response)

        # Get accountId
        self.accountId = list(auth_response["accounts"].keys())[0]

    @task
    def send_test(self):
        genId = self.genIdentity
        genId = genId.replace("Id_here", self.accountId)
        genId = genId.replace("email_here", random.choice(self.emailPool))
        genId = genId.replace("counter_here", "identity"+str(self.counter))
        genId = genId.replace("name_here", random.choice(self.namePool))

        self.client.post("/api", auth=(self.email, self.password), data=genId)

        self.counter += 1

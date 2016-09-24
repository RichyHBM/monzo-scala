package models

import monzo_scala.models.attachments._
import org.specs2.mutable.Specification

class AttachmentsModelsSpec extends Specification {
  "UploadAttachment Model" should {
    "Construct from json" in {
      val uploadAttachment = UploadAttachment.fromJson(ExampleJson.uploadAttachmentJson)

      uploadAttachment mustNotEqual null
      uploadAttachment.fileUrl must equalTo("https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png")
      uploadAttachment.uploadUrl must equalTo("https://mondo-image-uploads.s3.amazonaws.com/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png?AWSAccessKeyId=AKIAIR3IFH6UCTCXB5PQ\u0026Expires=1447353431\u0026Signature=k2QeDCCQQHaZeynzYKckejqXRGU%!D(MISSING)")
    }

    "Convert to json" in {
      val uploadAttachment = UploadAttachment("https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png",
        "https://mondo-image-uploads.s3.amazonaws.com/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png?AWSAccessKeyId=AKIAIR3IFH6UCTCXB5PQ\u0026Expires=1447353431\u0026Signature=k2QeDCCQQHaZeynzYKckejqXRGU%!D(MISSING)"
      )

      uploadAttachment.toJson() must equalTo(ExampleJson.uploadAttachmentJson)
    }
  }

  "RegisterAttachment Model" should {
    "Construct from json" in {
      val registerAttachment = RegisterAttachment.fromJson(ExampleJson.registerAttachmentJson)

      registerAttachment mustNotEqual null
      registerAttachment.attachment mustNotEqual null
      registerAttachment.attachment.id must equalTo("attach_00009238aOAIvVqfb9LrZh")
      registerAttachment.attachment.userId must equalTo("user_00009238aMBIIrS5Rdncq9")
      registerAttachment.attachment.externalId must equalTo("tx_00008zIcpb1TB4yeIFXMzx")
      registerAttachment.attachment.fileUrl must equalTo("https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png")
      registerAttachment.attachment.fileType must equalTo("image/png")
      registerAttachment.attachment.created must equalTo("2015-11-12T18:37:02Z")
    }

    "Convert to json" in {
      val registerAttachment = RegisterAttachment(Attachment(
        "attach_00009238aOAIvVqfb9LrZh",
        "user_00009238aMBIIrS5Rdncq9",
        "tx_00008zIcpb1TB4yeIFXMzx",
        "https://s3-eu-west-1.amazonaws.com/mondo-image-uploads/user_00009237hliZellUicKuG1/LcCu4ogv1xW28OCcvOTL-foo.png",
        "image/png",
        "2015-11-12T18:37:02Z"))

      registerAttachment.toJson() must equalTo(ExampleJson.registerAttachmentJson)
    }
  }

  "DeregisterAttachment Model" should {
    "Construct from json" in {
      val deregisterAttachment = DeregisterAttachment.fromJson(ExampleJson.deregisterAttachmentJson)

      deregisterAttachment mustNotEqual null
    }

    "Convert to json" in {
      val deregisterAttachment = DeregisterAttachment()

      deregisterAttachment.toJson() must equalTo(ExampleJson.deregisterAttachmentJson)
    }
  }
}
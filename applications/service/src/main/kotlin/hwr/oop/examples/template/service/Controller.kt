package hwr.oop.examples.template.service

import hwr.oop.examples.template.service.api.TrainReadApi
import hwr.oop.examples.template.service.api.TrainWriteApi
import hwr.oop.examples.template.service.model.CreateTrainRequest
import hwr.oop.examples.template.service.model.TrainCreatedResponse
import hwr.oop.examples.template.service.model.TrainResponse
import hwr.oop.examples.template.service.model.UpdateTrainRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller : TrainWriteApi, TrainReadApi {
	
	override fun createTrain(createTrainRequest: @Valid CreateTrainRequest?): ResponseEntity<TrainCreatedResponse> {
		TODO("Not yet implemented")
	}
	
	override fun updateTrain(
		trainId: String?,
		updateTrainRequest: @Valid UpdateTrainRequest?,
	): ResponseEntity<TrainResponse> {
		TODO("Not yet implemented")
	}
	
	override fun getTrain(trainId: String?): ResponseEntity<TrainResponse> {
		TODO("Not yet implemented")
	}
}

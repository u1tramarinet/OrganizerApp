package com.u1tramarinet.organizerapp.data.source.room

import com.u1tramarinet.organizerapp.data.repository.dto.ConfirmationState
import com.u1tramarinet.organizerapp.data.repository.dto.Event
import com.u1tramarinet.organizerapp.data.repository.dto.Fee
import com.u1tramarinet.organizerapp.data.repository.dto.Participant
import com.u1tramarinet.organizerapp.data.repository.dto.User
import com.u1tramarinet.organizerapp.data.repository.dto.Venue
import com.u1tramarinet.organizerapp.data.source.room.entity.ConfirmationStateColumn
import com.u1tramarinet.organizerapp.data.source.room.entity.EventEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.FeeEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.ParticipantEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.UserEntity
import com.u1tramarinet.organizerapp.data.source.room.entity.VenueEntity
import java.time.LocalDateTime

fun Event.toEventEntity() = EventEntity(
    eventId = id,
    title = title,
    date = date,
    venueId = venueId,
    needAttendance = needAttendance,
    needFee = needFee,
)

fun List<Event>.toEventEntities() = map(Event::toEventEntity)

fun EventEntity.toEvent() = Event(
    id = eventId,
    title = title,
    date = date,
    venueId = venueId,
    needAttendance = needAttendance,
    needFee = needFee,
)

fun List<EventEntity>.toEvents() = map(EventEntity::toEvent)

fun VenueEntity.toVenue() = Venue(
    id = venueId,
    name = name,
    postCode = postCode,
    address = address,
    url = url,
)

fun List<VenueEntity>.toVenues() = map(VenueEntity::toVenue)

fun Venue.toVenueEntity() = VenueEntity(
    venueId = id,
    name = name,
    postCode = postCode,
    address = address,
    url = url,
)

fun List<Venue>.toVenueEntities() = map(Venue::toVenueEntity)

fun Participant.toParticipantEntity(eventId: Int) = ParticipantEntity(
    participantId = id,
    eventId = eventId,
    userId = user.id,
    attendanceState = attendance.toConfirmationStateColumn(),
    paymentState = payment.toConfirmationStateColumn(),
    feeId = fee.id,
)

fun ConfirmationState.toConfirmationStateColumn(): ConfirmationStateColumn {
    val date: LocalDateTime? = if (this is ConfirmationState.Confirmed) confirmedDate else null
    val index = when (this) {
        is ConfirmationState.NotConfirmed -> 0
        is ConfirmationState.Confirmed -> 1
        else -> 2
    }
    return ConfirmationStateColumn(index = index, date = date)
}

fun ConfirmationStateColumn.toConfirmationState(): ConfirmationState {
    return if (index == 2) {
        ConfirmationState.Canceled
    } else if (index == 1 && date != null) {
        ConfirmationState.Confirmed(confirmedDate = date)
    } else {
        ConfirmationState.NotConfirmed
    }
}

fun UserEntity.toUser() = User(
    id = userId,
    name = name,
    phoneNumber = phoneNumber,
    mailAddress = mainAddress,
)

fun List<UserEntity>.toUsers() = map(UserEntity::toUser)

fun User.toUserEntity() = UserEntity(
    userId = id,
    name = name,
    phoneNumber = phoneNumber,
    mainAddress = mailAddress,
)

fun List<User>.toUserEntities() = map(User::toUserEntity)

fun FeeEntity.toFee(): Fee {
    if (feeId == 1) {
        return Fee.Free
    } else if (title != null) {
        return Fee.Named(
            id = feeId,
            title = title,
            price = price,
        )
    }
    return Fee.Unnamed(
        id = feeId,
        price = price,
    )
}

fun List<FeeEntity>.toFees() = map(FeeEntity::toFee)

fun Fee.toFeeEntity(eventId: Int): FeeEntity {
    val title: String? = if (this is Fee.Named) title else null
    return FeeEntity(
        feeId = id,
        eventId = eventId,
        title = title,
        price = price,
    )
}


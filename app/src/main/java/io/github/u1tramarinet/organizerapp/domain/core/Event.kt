package io.github.u1tramarinet.organizerapp.domain.core

sealed class Event(
    open val title: String,
) {
    class Registered(
        val id: Int = 0,
        override val title: String,
        val schedule: Schedule,
        val venue: Venue.Registered?,
        val confirmation: Confirmation,
    ) : Event(title = title)

    class Summary(
        val id: Int = 0,
        override val title: String,
        val schedule: Schedule,
    ) : Event(title = title) {
        companion object {
            fun from(registered: Registered): Summary {
                return Summary(
                    id = registered.id,
                    title = registered.title,
                    schedule = registered.schedule,
                )
            }
        }
    }

    class Draft(
        override val title: String,
        val schedule: Schedule = Schedule.today(),
        val venue: Venue?,
        val confirmation: Confirmation.Draft = Confirmation.Draft.initialValue(),
    ) : Event(title = title)
}
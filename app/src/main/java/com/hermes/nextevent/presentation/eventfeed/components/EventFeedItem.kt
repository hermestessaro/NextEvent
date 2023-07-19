package com.hermes.nextevent.presentation.eventfeed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hermes.nextevent.data.remote.model.Event

@Composable
fun EventFeedItem(
    event: Event,
    onItemClick: (Event) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()
        .clickable { onItemClick(event) }) {
        Column {
            EventImage(
                url = event.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 12.dp))
            )
            Text(
                text = event.title,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(8.dp, 12.dp, 4.dp, 8.dp)
            )
            Text(
                text = event.getFormattedDate(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    start = 8.dp,
                    bottom = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun EventItemPreview() {
    val event = Event(
        people = emptyList(),
        date = 1534784400000,
        description = "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\\n\\nNa ocasião, teremos bottons, bloquinhos e camisetas!\\n\\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \\n\\nAceitaremos todos os tipos de doação:\\n- guias e coleiras em bom estado\\n- ração (as que mais precisamos no momento são sênior e filhote)\\n- roupinhas \\n- cobertas \\n- remédios dentro do prazo de validade\"",
        image = "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        longitude = -51.2146267,
        latitude = -30.0392981,
        price = 29.99f,
        title = "Feira de adoção de animais na Redenção",
        id = "1"
    )
    EventFeedItem(event = event, onItemClick = {})
}
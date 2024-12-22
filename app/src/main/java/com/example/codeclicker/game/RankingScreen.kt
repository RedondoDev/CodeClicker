import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeclicker.load.DataBase
import com.example.codeclicker.start.YourCharacter
import com.example.codeclicker.ui.theme.quicksandFamily

@Composable
fun RankingScreen(character: YourCharacter, dataBase: DataBase) {

    val players = remember { mutableStateListOf<Pair<String, String>>() }

    LaunchedEffect(key1 = true) {
        val topPlayers = dataBase.getTop30CharactersByMoney()
        players.clear()
        players.addAll(topPlayers.map { it.name to "${it.money}$" })
    }

//    val players = listOf(
//        "Luru" to "188.007$",
//        "Claudiu" to "186.021$",
//        "Sebas" to "184.300$",
//        "Iñaki" to "184.199$",
//        "Prats" to "181.267$",
//        "Cebolletas" to "179.661$",
//        "Navas" to "172.100$",
//        "Sandra" to "166.021$",
//        "Víctor" to "166.010$",
//        "Javi-Hacker" to "160.808$",
//        "Rubén" to "157.996$",
//        "Ahmed" to "154.241$",
//        "Diego" to "151.440$",
//        "Jaime" to "146.277$",
//        "Alex" to "138.505$",
//        "Manu" to "138.505$",
//        "Dani" to "138.505$",
//        "Vicente" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$",
//        "Alex" to "138.505$"
//    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffcbd8e8))
            .padding(
                top = 8.dp,
                start = 25.dp,
                end = 25.dp,
                bottom = 16.dp
            )
    ) {
        Text(
            "Ranking",
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Color.White,
                    RoundedCornerShape(10.dp)
                )
                .border(
                    2.dp,
                    Color.Black,
                    RoundedCornerShape(10.dp)
                )
        ) {
            items(players.size) { index ->
                PlayerRow(
                    top = index + 1,
                    name = players[index].first,
                    money = players[index].second,
                    isEven = index % 2 == 0,
                )
            }
        }
    }
}

@Composable
fun PlayerRow(
    top: Int,
    name: String,
    money: String,
    isEven: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = when (top) {
                    1 -> Color(0xFFFFC107).copy(0.5f)
                    2 -> Color(0xFFBDBDBD).copy(0.5f)
                    3 -> Color(0xFFA65C00).copy(0.5f)
                    else -> if (isEven) Color(0xffcbd8e8).copy(0.6f) else Color(0xFFFFFFFF)
                }
            )
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = top.toString(),
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .width(40.dp)
                .padding(end = 5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = name,
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            ),
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = money,
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            ),
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(end = 15.dp),
        )
    }
}
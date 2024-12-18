import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeclicker.R
import com.example.codeclicker.load.DataBase
import com.example.codeclicker.start.YourCharacter
import com.example.codeclicker.ui.theme.quicksandFamily
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class Skill(
    val name: String,
    val image: Int,
    val description: String,
    val price: Int,
    val unlocked: Boolean
)

private val listSkills = listOf(
    Skill(
        "Funciones",
        R.drawable.functions,
        "Genera 2 líneas por clic en lugar de una.",
        10,
        false
    ),
    Skill(
        "Bots",
        R.drawable.bot,
        "Genera 2 líneas por clic en lugar de una.",
        50,
        false
    ),
    Skill(
        "Inteligencia Artificial",
        R.drawable.ai,
        "Genera 2 líneas por clic en lugar de una.",
        75,
        false
    ),
    Skill(
        "En producción",
        R.drawable.enproduccion,
        "Disponible próximamente. \nHabilidad en producción.",
        100,
        false
    ),
)

private val shapeForSharedElement = RoundedCornerShape(16.dp)

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GithubScreen(yourCharacter: YourCharacter, dataBase: DataBase) {

    var selectedSkill by remember { mutableStateOf<Skill?>(null) }
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .background(Color(0xffcbd8e8))
                .padding(6.dp)
        ) {
            Image(
                painterResource(R.drawable.coin),
                "Coin",
                modifier = Modifier
                    .size(25.dp)
                    .padding(
                        top = 1.dp,
                        end = 5.dp
                    )
            )
            LaunchedEffect(key1 = true) { yourCharacter.money = dataBase.getMoney()!! }
            Text(
                "${yourCharacter.money}€",
                style = TextStyle(
                    fontFamily = quicksandFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                ),
            )
        }
        SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xffcbd8e8))
                    .padding(
                        top = 0.dp,
                        start = 16.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listSkills) { skill ->
                    AnimatedVisibility(
                        visible = skill != selectedSkill,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut(),
                        modifier = Modifier.animateItem()
                    ) {
                        Box(
                            modifier = Modifier
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "${skill.name}-bounds"),
                                    animatedVisibilityScope = this,
                                    clipInOverlayDuringTransition = OverlayClip(
                                        shapeForSharedElement
                                    )
                                )
                                .background(Color.White, shapeForSharedElement)
                                .clip(shapeForSharedElement)
                        ) {
                            SkillContents(
                                skill = skill,
                                modifier = Modifier.sharedElement(
                                    state = rememberSharedContentState(key = skill.name),
                                    animatedVisibilityScope = this@AnimatedVisibility
                                ),
                                onClick = {
                                    selectedSkill = skill
                                }
                            )
                        }
                    }
                }
            }
            SkillEditDetails(
                dataBase,
                yourCharacter,
                skill = selectedSkill,
                onConfirmClick = {
                    selectedSkill = null
                }
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SkillEditDetails(
    dataBase: DataBase,
    yourCharacter: YourCharacter,
    skill: Skill?,
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = skill,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "SkillEditDetails"
    ) { targetSkill ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (targetSkill != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onConfirmClick()
                        }
                        .background(Color.Black.copy(alpha = 0.5f))
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "${targetSkill.name}-bounds"),
                            animatedVisibilityScope = this@AnimatedContent,
                            clipInOverlayDuringTransition = OverlayClip(shapeForSharedElement)
                        )
                        .background(Color.White, shapeForSharedElement)
                        .clip(shapeForSharedElement)
                ) {

                    SkillContents(
                        skill = targetSkill,
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = targetSkill.name),
                            animatedVisibilityScope = this@AnimatedContent,
                        ),
                        onClick = {
                            onConfirmClick()
                        }
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray.copy(alpha = 0.4f))
                            .padding(6.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        if (skill != null) {
                            Text(
                                skill.description,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp)
                            )
                            if (!skill.name.equals("En producción")) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp)
                                ) {
                                    Text(
                                        "${skill.price}€",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Button(
                                        colors = ButtonDefaults.buttonColors((Color(0xFF3c6391))),
                                        enabled = (yourCharacter.money >= skill.price),
                                        onClick = {
                                            println(yourCharacter.money)
                                            println(skill.price)
                                            yourCharacter.money -= skill.price
                                            dataBase.updateMoney(yourCharacter.money)
                                        }
                                    ) {
                                        Text(
                                            "Comprar",
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SkillContents(
    skill: Skill,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
    ) {
        Image(
            painter = painterResource(id = skill.image),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(20f / 9f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = skill.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 2.dp,
                    bottom = 2.dp,
                ),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
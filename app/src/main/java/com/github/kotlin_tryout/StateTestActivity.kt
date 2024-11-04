package com.github.kotlin_tryout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.kotlin_tryout.ui.theme.KotlinTryoutTheme

class StateTestActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar =
                        {
                            TopAppBar(title = { Text(text = "Sample App") })
                        },
                ) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Greeting3()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting3(
    viewModel: SampleStateViewModel =
        androidx.lifecycle.viewmodel.compose
            .viewModel(),
) {
    val state: String? by viewModel.name.collectAsStateWithLifecycle()
    ContentGrid(
        content =
//            remember {
            (1..20).map { DataItem("Item $it", "$it") },
//            },
    )
}

@Composable
fun ContentGrid(content: List<DataItem>) {
    LazyColumn(
        Modifier
            .fillMaxHeight(),
    ) {
        items(content) {
            ContentElement(
                item = it,
                modifier =
                    Modifier
                        .fillMaxWidth(0.4f)
                        .fillParentMaxHeight(0.2f),
            )
        }
    }
}

data class DataItem(
    val title: String,
    val price: String,
)

@Composable
fun ContentElement(
    item: DataItem,
    modifier: Modifier,
) {
    Card(
        modifier =
            modifier
                .padding(18.dp)
                .clip(RoundedCornerShape(10))
                .clickable(enabled = true, onClick = { }),
        colors =
            CardDefaults.cardColors(
                containerColor = Color(200, 200, 238, 226),
                contentColor = Color.Black,
            ),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .clip(CircleShape),
            )
            /* AsyncImage(
                  model = item.url,
                  contentDescription = item.title,
                  modifier = Modifier
                      .size(200.dp, 200.dp)
                      .clip(RoundedCornerShape(10)),
                  contentScale = ContentScale.FillWidth),*/
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(Modifier.height(10.dp))
                Text(item.title, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                Text(item.price, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

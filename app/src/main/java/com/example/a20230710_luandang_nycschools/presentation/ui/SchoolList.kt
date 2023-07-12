package com.example.a20230710_luandang_nycschools.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.a20230710_luandang_nycschools.R
import com.example.a20230710_luandang_nycschools.data.api.NetworkResult
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponseEntity
import com.example.a20230710_luandang_nycschools.data.model.SATResponseEntity
import com.example.a20230710_luandang_nycschools.data.model.SATScoreResponse
import com.example.a20230710_luandang_nycschools.presentation.util.getPhoneString
import com.example.a20230710_luandang_nycschools.presentation.util.getWebsiteString
import com.example.a20230710_luandang_nycschools.presentation.viewmodel.SchoolViewModel
import com.example.a20230710_luandang_nycschools.ui.theme.dp_1
import com.example.a20230710_luandang_nycschools.ui.theme.dp_10
import com.example.a20230710_luandang_nycschools.ui.theme.dp_15
import com.example.a20230710_luandang_nycschools.ui.theme.dp_5
import com.example.a20230710_luandang_nycschools.ui.theme.sp_14
import com.example.a20230710_luandang_nycschools.ui.theme.sp_20

@Composable
fun SchoolList(schoolViewModel: SchoolViewModel){
    val schoolResult by schoolViewModel.schoolResult.observeAsState()
    var message by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true)}

    LaunchedEffect(Unit){
        schoolViewModel.getSchools()
    }
    when(schoolResult){
        is NetworkResult.Loading ->{
            loading = true
        }
        is NetworkResult.Success -> {
            loading = false
            val schools = (schoolResult as NetworkResult.Success<HighSchoolResponse>).data ?: emptyList()
            if(schools.isEmpty()){
                showErrorMess(mess = stringResource(R.string.no_data))
            }else{
                LazyColumn{
                    items(schools){school ->
                        SchoolItem(school = school, schoolViewModel = schoolViewModel)
                    }
                }
            }
        }
        is NetworkResult.Error -> {
            loading = false
            message = (schoolResult as NetworkResult.Error<HighSchoolResponse>).mess.toString()
            showErrorMess(mess  = message)

        }
        is NetworkResult.Exception -> {
            loading = false
            message = (schoolResult as NetworkResult.Exception<HighSchoolResponse>).e.message.toString()
            showErrorMess(mess = message)
        }
        else -> {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "loading"
            )
        }
    }
   LoadingIndicator(loading = loading)
}

@Composable
fun LoadingIndicator(loading: Boolean) {
    val strokeWidth = dp_5
    if(loading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            Color.Blue,
                            radius = size.width / 2 - strokeWidth.toPx() / 2,
                            style = Stroke(strokeWidth.toPx())
                        )
                    },
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        }

    }
}

@Composable
fun SchoolItem(school: HighSchoolResponseEntity, schoolViewModel: SchoolViewModel){
    val context = LocalContext.current
    var displayingInfo by remember { mutableStateOf(false)}

    Card(
        modifier = Modifier
            .testTag("schoolCard")
            .fillMaxWidth()
            .padding(dp_15)
            .clickable(onClick = {
                displayingInfo = !displayingInfo
            }),
        shape = RoundedCornerShape(dp_15),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        elevation = CardDefaults.cardElevation(dp_15)
    ){
        Column(
            modifier = Modifier.padding(dp_15),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = school.schoolName,
                fontWeight = FontWeight.Bold,
                fontSize = sp_20,
            )
            Spacer(
                modifier = Modifier
                    .height(dp_1)
                    .padding(
                        start = dp_10,
                        end = dp_10
                    )
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
            ClickableText(
                text = getPhoneString(school.phoneNumber, context),
                onClick = {
                    val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${school.phoneNumber}"))
                    context.startActivity(phoneIntent)
                }
            )
            ClickableText(
                text = getWebsiteString(school.website, context),
                onClick = {
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://${school.website}"))
                    context.startActivity(webIntent)
                }
            )
            Text(
                text = "${school.schoolEmail}",
                fontSize = sp_14
            )

            if (displayingInfo) {
                Spacer(modifier = Modifier.height(dp_10))
                SATScoresLayout(dbn = school.dbn, schoolViewModel = schoolViewModel)
                Spacer(modifier = Modifier.height(dp_10))
                Text(
                    text = "${school.overviewParagraph}",
                    fontSize = sp_14,
                )
            }
        }
    }
}

@Composable
fun SATScoresLayout(dbn: String, schoolViewModel: SchoolViewModel) {
    val satScores by schoolViewModel.SATResult.observeAsState()
    var message by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true)}

    LaunchedEffect(Unit){
        schoolViewModel.getSATScores(dbn)
    }
    when(satScores){
        is NetworkResult.Loading ->{
            loading = true
        }
        is NetworkResult.Success -> {
            loading = false
            val satScore = (satScores as NetworkResult.Success<SATScoreResponse>).data ?: emptyList()
            if(satScore.isEmpty()){
                showErrorMess(mess = stringResource(R.string.no_data))
            }else{
                SATCard(satScore)
            }

        }
        is NetworkResult.Error -> {
            loading = false
            message = (satScores as NetworkResult.Error<SATScoreResponse>).mess.toString()
            showErrorMess(mess  = message)

        }
        is NetworkResult.Exception -> {
            loading = false
            message = (satScores as NetworkResult.Exception<SATResponseEntity>).e.message.toString()
            showErrorMess(mess = message)
        }
        else -> {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "loading"
            )
        }
    }
}

@Composable
fun SATCard(satScore: List<SATResponseEntity>) {
    Column(
        Modifier
            .testTag("SAT Column")
            .padding(dp_10)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier
            .height(dp_1)
            .background(Color.Gray)
        )
        Text(
            text= "SAT Averages:",
            fontWeight = FontWeight.Bold
        )
        satScore?.get(0)?.let {
            Text("Math: ${it.sat_math_avg_score}")
            Text("Reading: ${it.sat_critical_reading_avg_score}")
            Text("Writing: ${it.sat_writing_avg_score}")
        }
    }
}

@Composable
fun showErrorMess(mess: String){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = mess
    )
}


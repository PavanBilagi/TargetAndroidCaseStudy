package com.target.targetcasestudy.ui.dealdetails

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.target.targetcasestudy.R
import com.target.targetcasestudy.models.Deal
import com.target.targetcasestudy.models.Price
import com.target.targetcasestudy.ui.base.Status
import com.target.targetcasestudy.ui.base.UIModePreviews
import com.target.targetcasestudy.ui.ui.theme.MainAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealDetailsScreen(
    modifier: Modifier = Modifier,
    state: Status<Deal>,
    onBackPressed: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_screen_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
            ) {
                Text(
                    text = stringResource(R.string.add_to_cart),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }

    ) { innerPadding ->
        when (state) {
            is Status.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(64.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            is Status.Success -> {
                DealDetailsSuccess(modifier.padding(innerPadding), state.data)
            }

            is Status.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    state.throwable?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DealDetailsSuccess(
    modifier: Modifier,
    deal: Deal,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 40.dp, start = 8.dp, end = 8.dp)
        ) {

            GlideImage(
                model = deal.imageUrl,
                contentDescription = deal.title,
                contentScale = ContentScale.Crop,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(328.dp),

                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = deal.title ?: "",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                if (deal.salePrice?.displayString?.isNotEmpty() == true) {
                    Text(
                        text = deal.salePrice.displayString,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,

                        )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.align(Alignment.Bottom),
                        text = stringResource(R.string.reg) + " " + (deal.regularPrice?.displayString
                            ?: ""),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                } else {
                    Text(
                        text = deal.regularPrice?.displayString ?: "",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Text(
                text = deal.fulfillment ?: "",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 24.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.product_details),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface

            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = deal.description ?: "",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Light,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@UIModePreviews
@Composable
private fun DealDetailsScreenPreview() {
    MainAppTheme {
        DealDetailsScreen(state = Status.Success(
            Deal(
                id = "0",
                title = "Product 1",
                description = "This is product number 1",
                fulfillment = "online",
                availability = "In stock",
                aisle = "b2",
                imageUrl = "https://appstorage.target.com/app-data/native-tha-images/1.jpg",
                regularPrice = Price(
                    amountInCents = 22999,
                    currencySymbol = "$",
                    displayString = "$22.99"
                ),
                salePrice = Price(
                    amountInCents = 22999,
                    currencySymbol = "$",
                    displayString = "$22.99"
                )
            )
        ),
            onBackPressed = {})
    }
}

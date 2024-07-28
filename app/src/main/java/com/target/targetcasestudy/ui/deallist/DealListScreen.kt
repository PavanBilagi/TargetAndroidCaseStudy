package com.target.targetcasestudy.ui.deallist

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun DealListScreen(
    modifier: Modifier = Modifier,
    state: Status<List<Deal>>,
    dealClicked: (String) -> Unit
) {
    when (state) {
        is Status.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        is Status.Success -> {
            DealListScreenSuccess(modifier, state.data, dealClicked)
        }

        is Status.Error -> {
            Toast.makeText(LocalContext.current, state.throwable?.message, Toast.LENGTH_SHORT)
                .show()
        }

    }

}


@Composable
fun DealListScreenSuccess(modifier: Modifier, list: List<Deal>, dealClicked: (String) -> Unit) {
    LazyColumn(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        items(items = list, key = { it.id ?: "" }) { deal ->
            DealItem(deal, dealClicked)
            HorizontalDivider(thickness = 1.dp)
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DealItem(deal: Deal, onDealItemClicked: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDealItemClicked(deal.id.toString()) }
            .padding(4.dp)
    ) {

        GlideImage(
            model = deal.imageUrl,
            contentDescription = deal.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterVertically)
                .size(140.dp),
        )

        Column(modifier = Modifier.padding(16.dp)) {
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
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = deal.title ?: "",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = deal.availability ?: "",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.in_aisle) + " " + deal.aisle,
                    fontSize = 16.sp,
                )
            }
        }

    }
}


@UIModePreviews
@Composable
private fun DealListScreenPreview() {
    MainAppTheme {
        DealListScreen(state = Status.Success(
            listOf(
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
                ),
                Deal(
                    id = "1",
                    title = "Product 2",
                    description = "This is product number 2",
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
            )


        ),
            dealClicked = {})
    }
}
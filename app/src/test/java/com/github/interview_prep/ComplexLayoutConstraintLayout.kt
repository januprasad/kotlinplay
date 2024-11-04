package com.github.kotlin_tryout

import androidx.compose.runtime.Composable

@Composable
fun ComplexLayoutConstraintLayout(
    title: String,
    imageUrl: String,
    description: String,
    author: String,
    onLikeClicked: () -> Unit,
) {
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//        val imageRef = createRef()
//        val contentRef = createRef()
//
//        // Image with rounded corners
//        Text(
//            text = "Hello",
//            modifier =
//                Modifier
//                    .constrainAs(imageRef) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }.clip(RoundedCornerShape(8.dp)),
//        )
//
//        // Content positioned over the image
//        Column(
//            modifier =
//                Modifier
//                    .constrainAs(contentRef) {
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.start, margin = 16.dp)
//                        end.linkTo(parent.end, margin = 16.dp)
//                    }.padding(vertical = 8.dp), // Reduce vertical padding for better alignment
//        ) {
//            // Title
//            Text(text = title, style = MaterialTheme.typography.headlineSmall)
//
//            // Description
//            Text(text = description, style = MaterialTheme.typography.bodyMedium)
//
//            // Row for author and like button
//            Row(modifier = Modifier.fillMaxWidth()) {
//                // Author text
//                Text(text = "by $author", style = MaterialTheme.typography.bodyLarge)
//
//                // Spacer between author and like button
//                Spacer(modifier = Modifier.weight(1f))
//
//                // Like button with icon
//                IconButton(onClick = onLikeClicked) {
//                    Icon(Icons.Filled.Favorite, contentDescription = "Like this item")
//                }
//            }
//        }
//
//        // Constrain content to be above the bottom of the image
//        createVerticalChain(imageRef.bottom, contentRef.top)
//    }
}

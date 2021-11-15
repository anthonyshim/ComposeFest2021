package com.anthony.week2_layouts_anthony

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import coil.compose.rememberImagePainter
import com.anthony.week2_layouts_anthony.ui.theme.Week2_layouts_anthonyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week2_layouts_anthonyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LayoutsCodelab {
                        BodyContent4()
                    }
                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable(onClick = {})
        .padding(16.dp)) {
        Surface(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically), shape = CircleShape, color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320)
fun PhotographerCardPreview() {
    Week2_layouts_anthonyTheme {
        PhotographerCard()
    }
}

@Composable
fun TopAppBarExample() {
    TopAppBar(title = { Text(text = "Page title", maxLines = 2) }, navigationIcon = { Icon(imageVector = Icons.Sharp.Close, contentDescription = null) })
}

@Composable
@Preview(showBackground = true, widthDp = 320)
fun TopAppBarExamplePreview() {
    Week2_layouts_anthonyTheme {
        TopAppBarExample()
    }
}

@Composable
fun LayoutsCodelab(bodyContent: @Composable () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "LayoutsCodelab")
        }, actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        })
    }) { innerPadding ->
        bodyContent()
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun LayoutsCodelabPreview() {
    Week2_layouts_anthonyTheme {
        LayoutsCodelab {
            BodyContent()
        }
    }
}

@Composable
fun SimpleList() {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(100) {
            Text("Item $it")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun SimpleListPreview() {
    Week2_layouts_anthonyTheme {
        SimpleList()
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"), contentDescription = "Android Logo", modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item $index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ImageList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row {
            Button(modifier = Modifier.weight(1f), onClick = { coroutineScope.launch { scrollState.animateScrollToItem(0) } }) {
                Text("Scroll to the top")
            }
            Button(modifier = Modifier.weight(1f), onClick = { coroutineScope.launch { scrollState.animateScrollToItem(listSize - 1) } }) {
                Text("Scroll to the end")
            }
        }
        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun ImageListPreview() {
    Week2_layouts_anthonyTheme {
        ImageList()
    }
}

fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = this.then(layout { measurable, constraints ->
    val placeable = measurable.measure(constraints = constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        placeable.placeRelative(0, placeableY)
    }
})

@Composable
fun TextPaddingCompare() {
    Row(verticalAlignment = Alignment.Top) {
        Text(
            "Hi there!", modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .firstBaselineToTop(32.dp)
        )
        Text(
            "Hi there!", modifier = Modifier
                .background(color = MaterialTheme.colors.secondary)
                .padding(top = 32.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextPaddingComparePreview() {
    Week2_layouts_anthonyTheme {
        TextPaddingCompare()
    }
}

@Composable
fun MyOwnColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        var yPosition = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun BodyContent2(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun BodyContent2Preview() {
    Week2_layouts_anthonyTheme {
        BodyContent2()
    }
}

@Composable
fun StaggeredGrid(modifier: Modifier = Modifier, rows: Int = 3, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val rowWidths = IntArray(rows) { 0 }
        val rowHeights = IntArray(rows) { 0 }
        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = Math.max(rowHeights[row], placeable.height)
            placeable
        }

        val width = rowWidths.maxOrNull()?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth
        val height = rowHeights.sumOf { it }.coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        layout(width = width, height = height) {
            val rowX = IntArray(rows) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(x = rowX[row], y = rowY[row])
                rowX[row] += placeable.width
            }
        }
    }
}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    Card(modifier = modifier, border = BorderStroke(color = Color.Black, width = Dp.Hairline), shape = RoundedCornerShape(8.dp)) {
        Row(modifier = Modifier.padding(8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(width = 16.dp, height = 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    Week2_layouts_anthonyTheme {
        Chip(text = "Hi there")
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary", "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy", "Religion", "Social sciences", "Technology", "TV", "Writing"
)

@Composable
fun BodyContent3(modifier: Modifier = Modifier) {
    StaggeredGrid(modifier = modifier, rows = 5) {
        topics.forEach { topic ->
            Chip(modifier = Modifier.padding(8.dp), text = topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyContent3Preview() {
    Week2_layouts_anthonyTheme {
        BodyContent3()
    }
}

@Stable
fun Modifier.padding(all: Dp) = this.then(PaddingModifier(start = all, top = all, end = all, bottom = all, rtlAware = true))

private class PaddingModifier(val start: Dp = 0.dp, val top: Dp = 0.dp, val end: Dp = 0.dp, val bottom: Dp = 0.dp, val rtlAware: Boolean) : LayoutModifier {
    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        val horizontal = start.roundToPx() + end.roundToPx()
        val vertical = top.roundToPx() + bottom.roundToPx()

        val placeable = measurable.measure(constraints = constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(width = placeable.width + horizontal)
        val height = constraints.constrainHeight(height = placeable.height + vertical)
        return layout(width = width, height = height) {
            if (rtlAware) {
                placeable.placeRelative(x = start.roundToPx(), y = top.roundToPx())
            } else {
                placeable.place(x = start.roundToPx(), y = top.roundToPx())
            }
        }
    }
}

@Composable
fun BodyContent4(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
            .size(200.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        StaggeredGrid {
            topics.forEach { topic ->
                Chip(modifier = Modifier.padding(all = 8.dp), text = topic)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyContent4Preview() {
    Week2_layouts_anthonyTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "LayoutsCodelab")
            }, actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            })
        }) {
            BodyContent4()
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()
        Button(onClick = {}, modifier = Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text("Button 1")
        }
        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            linkTo(start = parent.start, end = parent.end)
//            centerHorizontallyTo(parent)
//            start.linkTo(button.start)
//            end.linkTo(button.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(onClick = {}, modifier = Modifier.constrainAs(button2) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(barrier)
        }) {
            Text("Button 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutContentPreview() {
    Week2_layouts_anthonyTheme {
        ConstraintLayoutContent()
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(text = "This is a very very very very very very very long text", modifier = Modifier.constrainAs(text) {
            linkTo(start = guideline, end = parent.end)
            width = Dimension.preferredWrapContent.atLeast(100.dp)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun LargeConstraintLayoutPreview() {
    Week2_layouts_anthonyTheme {
        LargeConstraintLayout()
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = decoupledConstraints(margin = if (maxWidth < maxHeight) { 16.dp } else { 32.dp })
//        val constraints = if (maxWidth < maxHeight) {
//            decoupledConstraints(margin = 16.dp)
//        } else {
//            decoupledConstraints(margin = 32.dp)
//        }
        ConstraintLayout(constraints) {
            Button(onClick = {}, modifier = Modifier.layoutId("button")) {
                Text("Button")
            }
            Text(text = "Text", modifier = Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp) : ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(anchor = parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(anchor = button.bottom, margin = margin)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DecoupledConstraintLayoutPreview() {
    Week2_layouts_anthonyTheme {
        DecoupledConstraintLayout()
    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(modifier = Modifier
            .weight(1f)
            .padding(start = 4.dp)
            .wrapContentWidth(Alignment.CenterHorizontally), text = text1)
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(modifier = Modifier
            .weight(1f)
            .padding(end = 4.dp)
            .wrapContentWidth(Alignment.CenterHorizontally), text = text2)
    }
}

@Preview(showBackground = true)
@Composable
fun TwoTextsPreview() {
    Week2_layouts_anthonyTheme {
        TwoTexts(text1 = "Hi", text2 = "there")
    }
}
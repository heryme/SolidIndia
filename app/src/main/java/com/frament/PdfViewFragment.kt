package com.frament

import android.os.Bundle
import android.view.*
import com.github.barteksc.pdfviewer.PDFView
import com.solid1972.R
import com.solid1972.activity.MainActivity
import java.io.File
import java.io.InputStream


class PdfViewFragment : BaseFrament(){
    private val TAG: String = javaClass.simpleName
    private var rootView: View? = null
    var pdfView: PDFView? = null
    private val FILENAME = "pdf_solid.pdf"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_pdf_view, container, false)
        initIDs(rootView!!)
        initComponent()
        initToolbar()
        initData()
        initListeners()
        return rootView
    }

    override fun initComponent() {

    }



    override fun initToolbar() {
        (context as MainActivity).ivbarToolbar.visibility = View.GONE
        (context as MainActivity).llBackMain.visibility = View.VISIBLE
    }

    override fun initListeners() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu.findItem(R.id.filter)
        item.setVisible(false)

    }

    override fun initData() {
        showPdf()
    }

    override fun initIDs(rootView: View) {
        pdfView = rootView.findViewById(R.id.pdfView)
    }

    fun showPdf(){
        val file = File(activity?.cacheDir, FILENAME)
        if (!file.exists()) {
            try {
                val asset: InputStream = activity?.assets?.open(FILENAME)!!
                pdfView!!.fromStream(asset)
                    .pages(0, 2, 1, 3, 3, 3)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(0)
                    .load()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else{
            showSnackBar("Does not Open PDF")
        }

    }

}

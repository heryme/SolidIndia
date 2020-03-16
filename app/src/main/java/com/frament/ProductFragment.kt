package com.frament


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapter.ProductMainAdapter
import com.model.ProductResponse
import com.rest.ApiInitialize
import com.rest.ApiRequest
import com.rest.ApiResponseInterface
import com.rest.ApiResponseManager
import com.solidindia.R
import com.solidindia.activity.MainActivity
import com.utils.isNetWork



/**
 * A simple [Fragment] subclass.
 */
class ProductFragment : BaseFrament(),ApiResponseInterface {
    var TAG: String = javaClass.simpleName
    private var rootView: View? = null
    private var searchView: SearchView? = null
    private lateinit var rvProduct: RecyclerView
    private lateinit var tvProductTitle: TextView
    private lateinit var productMainAdapter: ProductMainAdapter
    private var productList: ArrayList<ProductResponse.Data>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_product, container, false)
        initIDs(rootView!!)
        initComponent()
        initToolbar()
        var langTyape = sessionManager["type",""]
        Log.e(TAG,"LanType-->$langTyape")
        callProductListAPI(langTyape)
        return  rootView
    }

    override fun initComponent() {
      }

    override fun initToolbar() {
        (context as MainActivity).ivbarToolbar.visibility = View.GONE
        (context as MainActivity).llBackMain.visibility = View.VISIBLE
    }

    override fun initListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initIDs(rootView: View) {
        rvProduct = rootView.findViewById(R.id.rvProduct)
        tvProductTitle = rootView.findViewById(R.id.tvProductTitle)
    }

    private fun setAdpater() {
        ///val productList: ArrayList<ProductResponse.Data.Category> = ArrayList()

        productMainAdapter = ProductMainAdapter(activity!!,activity!!, productList!!,true)
        rvProduct.adapter = productMainAdapter
        rvProduct.layoutManager = LinearLayoutManager(activity)

    }


    override fun getApiResponse(apiResponseManager: ApiResponseManager<*>) {
        when (apiResponseManager.type) {
            100 -> {
                var model: ProductResponse
                model = apiResponseManager.response as ProductResponse
                Log.e(TAG, "Event List Response:- ${model}")
                if(model.statusCode ==200) {
                    productList = ArrayList()
                    productList?.addAll(model.data)
                    // productMainAdapter.notifyDataSetChanged()
                    setAdpater()
                }
            }
        }
    }

    override fun onFailure(
        apiResponseManager: ApiResponseManager<*>,
        error_message: String,
        error: Boolean
    ) {
        if (!error) {
            showSnackBar(error_message)
        }
    }

    private fun callProductListAPI(type:String) {
        if (isNetWork(activity!!)) {
            ApiRequest(activity!!, ApiInitialize.initialize(ApiInitialize.MAIN_URL_API).productList(type),
                100, true, this)
        }else {
            showSnackBar(activity!!.resources.getString(R.string.internet_not_available))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val overflowMenu = menu.findItem(R.id.overflow)
        val lan = menu.findItem(R.id.filter)
        overflowMenu.setVisible(false)
        lan.setVisible(false)

        // Associate searchable configuration with the SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).getActionView() as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView!!.setMaxWidth(Integer.MAX_VALUE)

        // listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                productMainAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                productMainAdapter.getFilter().filter(query)
                return false
            }
        })

        searchView!!.setOnCloseListener(SearchView.OnCloseListener {
            (context as MainActivity).ivbarToolbar.visibility = View.VISIBLE
            false
        })

        searchView!!.setOnSearchClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                (context as MainActivity).ivbarToolbar.visibility = View.GONE
            }
        })
    }



   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_search) {
            true
        } else if(id == R.id.menuEng){
            sessionManager.put("type","en")
            //callProductListAPI("en")
            true
        }
        else if(id == R.id.menuArabian){
            sessionManager.put("type","ar")
            //callProductListAPI("ar")
            true
        }
        else if(id == R.id.menuSpanish){
            sessionManager.put("type","sp")
           // callProductListAPI("sp")
            true
        }
        else if(id == R.id.menuChinese){
            sessionManager.put("type","ch")
            //callProductListAPI("ch")
            true
        }
        else if(id == R.id.menuFrench){
            sessionManager.put("type","fr")
            //callProductListAPI("fr")
            true
        }
        else if(id == R.id.menuTamil){
            sessionManager.put("type","tm")
            //callProductListAPI("tm")
            true
        }
        else if(id == R.id.menuTelugu){
            sessionManager.put("type","te")
            //callProductListAPI("te")
            true
        }

        else super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuEng = menu.findItem(R.id.menuEng)
        if (menuEng != null)
            menuEng.isVisible = false

        val menuArabian = menu.findItem(R.id.menuArabian)
        if (menuArabian != null)
            menuArabian.isVisible = false

        val menuSpanish = menu.findItem(R.id.menuSpanish)
        if (menuSpanish != null)
            menuSpanish.isVisible = false

        val menuChinese = menu.findItem(R.id.menuChinese)
        if (menuChinese != null)
            menuChinese.isVisible = false

        val menuFrench = menu.findItem(R.id.menuFrench)
        if (menuFrench != null)
            menuFrench.isVisible = false

        val menuTamil = menu.findItem(R.id.menuTamil)
        if (menuTamil != null)
            menuTamil.isVisible = false

        val menuTelugu = menu.findItem(R.id.menuTelugu)
        if (menuTelugu != null)
            menuTelugu.isVisible = false
    }*/



}
